using com.tvd12.ezyfoxserver.client.handler;
using com.tvd12.ezyfoxserver.client.request;

namespace Network.Handler
{
    class HandshakeHandler : EzyHandshakeHandler
    {
        protected override EzyRequest getLoginRequest()
        {
            return new EzyLoginRequest("example", "cuongnmx", "password");
        }
    }
}