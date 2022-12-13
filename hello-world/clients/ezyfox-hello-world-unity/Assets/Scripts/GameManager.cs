using Configs;
using Network;
using UnityEngine;
using UnityEngine.UI;

public class GameManager : MonoBehaviour
{
    [SerializeField] private Text urlText;

    public void OnConnectButtonClick()
    {
        connectToServerBySocket();
    }
    
    private void connectToServerBySocket()
    {
        urlText.text = $"Creating socket to {GameConfig.host}:{GameConfig.port}";
        NetworkManager.Instance.createWebsocket();
    }
}
