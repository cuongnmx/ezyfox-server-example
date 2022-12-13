using com.tvd12.ezyfoxserver.client;
using com.tvd12.ezyfoxserver.client.config;
using com.tvd12.ezyfoxserver.client.constant;
using com.tvd12.ezyfoxserver.client.evt;
using com.tvd12.ezyfoxserver.client.handler;
using com.tvd12.ezyfoxserver.client.util;
using Configs;
using Network.Handler;

namespace Network
{
    public class SocketProxy : EzyLoggable
    {
        private static readonly SocketProxy Instance = new SocketProxy();

        private EzyClient client;
        private string host;
        private int port;

        public EzyClient Client { get => client; }
        public string Host { get => host; }
        public int Port { get => port; }

        public static SocketProxy getInstance()
        {
            return Instance;
        }

        public EzyClient setup(string host, int port)
        {
            this.host = host;
            this.port = port;

            logger.debug("Setting up socket client");
            var config = EzyClientConfig.builder()
                .clientName(GameConfig.ZoneName)
                .pingConfigBuilder()
                .pingPeriod(1000)
                .maxLostPingCount(5)
                .done()
                .build();

            var clients = EzyClients.getInstance();
            client = clients.newDefaultClient(config);
            var setup = client.setup();
            setup.addEventHandler(EzyEventType.DISCONNECTION, new DisconnectionHandler());
            setup.addDataHandler(EzyCommand.HANDSHAKE, new HandshakeHandler());

            setup.addEventHandler(EzyEventType.CONNECTION_SUCCESS, new ConnectionSuccessHandler());
            setup.addEventHandler(EzyEventType.CONNECTION_FAILURE, new EzyConnectionFailureHandler());
            setup.addDataHandler(EzyCommand.LOGIN, new LoginSuccessHandler());
            setup.addDataHandler(EzyCommand.LOGIN_ERROR, new EzyLoginErrorHandler());
            
            return client;
        }

        public void connect()
        {
            client.connect(host, port);
        }

    }
}