using com.tvd12.ezyfoxserver.client.evt;
using com.tvd12.ezyfoxserver.client.handler;

namespace Network.Handler
{
    class DisconnectionHandler : EzyDisconnectionHandler
    {
        protected override void postHandle(EzyDisconnectionEvent evt)
        {
            logger.info("event = " + evt);
        }
    }
}