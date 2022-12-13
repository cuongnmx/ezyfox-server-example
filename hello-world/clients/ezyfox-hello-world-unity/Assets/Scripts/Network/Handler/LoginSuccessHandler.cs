using com.tvd12.ezyfoxserver.client.entity;
using com.tvd12.ezyfoxserver.client.handler;
using UnityEngine;

namespace Network.Handler
{
    public class LoginSuccessHandler : EzyDataHandler
    {
        public void handle(EzyArray data)
        {
            Debug.Log("LoginSuccessHandler " + data);
        }
    }
}