using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace MyWebAPIBamiBread.Views
{
    public partial class Admin : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void btnSend_Click(object sender, EventArgs e)
        {
            try
            {

          
                Controllers.TokenDeviceController fcmController = new Controllers.TokenDeviceController();
                List<TokenDevice> dsFcm = fcmController.getTokenDevices();
                WebRequest tRequest;
                string applicationID = "AAAAiAwUwwg:APA91bHHuwQSDh4yhLRHAolkKe6G-XsnLBkWgo8n5n8gZwLcT1a1J0UQND8ewkyvVfj83iVRxG6b5GhW2sR83DQ5sN29sIr8ZYKhXa079LKzGOVIbGqKlFdokhegdF-4GLNTJbmc_tGX";
                string senser_id = "584318239496";
                //thiết lập fcm send
                tRequest = WebRequest.Create("https://fcm.googleapis.com/fcm/send");
                tRequest.Method = "POST";
                tRequest.UseDefaultCredentials = true;
                tRequest.PreAuthenticate = true;
                tRequest.Credentials = CredentialCache.DefaultNetworkCredentials;
                //định dạng JSON
                tRequest.ContentType = "application/json";



                tRequest.Headers.Add(string.Format("Authorization: key={0}", applicationID));

                tRequest.Headers.Add(string.Format("Sender: id={0}", senser_id));

                string[] arrRegid = dsFcm.Select(x => x.Token).ToArray();
                string RegArr = string.Empty;
                RegArr = string.Join("\",\"", arrRegid);
                string postData = "{\"registration_ids\":[\"" + RegArr + "\"],\"data\":{\"message\":\"" + txtMessage.Text + "\",\"body\":\"" + txtMessage.Text + "\",\"title\":\"" + txtTitle.Text + "\",\"collapse_key\":\"" + txtMessage.Text + "\"}}";
                Console.WriteLine(postData);
                Byte[] byteArray = Encoding.UTF8.GetBytes(postData);

                tRequest.ContentLength = byteArray.Length;
                Stream dataStream = tRequest.GetRequestStream();
                dataStream.Write(byteArray, 0, byteArray.Length);
                dataStream.Close();

                WebResponse tpResponse = tRequest.GetResponse();

                dataStream = tpResponse.GetResponseStream();

                StreamReader tReader = new StreamReader(dataStream);
                String sResponseFromSever = tReader.ReadToEnd();

                txtKQ.Text = sResponseFromSever;
                tReader.Close();
                dataStream.Close();
                tpResponse.Close();

            }
            catch (Exception ex)
            {
                txtKQ.Text = ex.Message;
            }
        }
    }
}