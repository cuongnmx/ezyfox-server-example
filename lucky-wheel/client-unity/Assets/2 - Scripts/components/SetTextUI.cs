using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class SetTextUI : MonoBehaviour
{
    public void SetText(string value)
    {
        this.GetComponent<Text>().text = value;
    }
}