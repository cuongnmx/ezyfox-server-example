using com.tvd12.ezyfoxserver.client;
using com.tvd12.ezyfoxserver.client.logger;
using Configs;
using UnityEngine;
using Utils;

namespace Network
{
    public class NetworkManager : MonoBehaviour
    {
        private EzyClient client;
        private EzyLogger logger;

        public static NetworkManager Instance { get; private set; }

        private bool initializedSocket = false;

        private void Awake()
        {
            // If go back to current scene, don't make duplication
            if (Instance != null)
            {
                Destroy(gameObject);
            }
            else
            {
                Instance = this;
                DontDestroyOnLoad(gameObject);
            }
        }

        private void Start()
        {
            // Enable EzyLogger
            EzyLoggerFactory.setLoggerSupply(type => new UnityLogger(type));
            logger = EzyLoggerFactory.getLogger<NetworkManager>();
        }

        /***************** Websocket ***************/
        public void createWebsocket()
        {
            if (!initializedSocket)
            {
                client = SocketProxy.getInstance().setup(GameConfig.host, GameConfig.port);
                initializedSocket = true;
            }

            SocketProxy.getInstance().connect();
        }

        private void Update()
        {
            // Main thread pulls data from socket
            if (initializedSocket)
                client.processEvents();
        }
    }
}