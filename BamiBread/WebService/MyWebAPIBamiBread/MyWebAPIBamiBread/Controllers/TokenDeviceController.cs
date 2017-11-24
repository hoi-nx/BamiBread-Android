using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace MyWebAPIBamiBread.Controllers
{
    public class TokenDeviceController : ApiController
    {

        [HttpGet]
        public List<TokenDevice> getTokenDevices()
        {
            BamiBreadDataContext context = new BamiBreadDataContext();
            List<TokenDevice> listToken= context.TokenDevices.ToList();
            return listToken;
            
        }


        [HttpPost]
        public bool saveToken(String token)
        {
            try
            {
                BamiBreadDataContext context = new BamiBreadDataContext();
                TokenDevice tokenD = context.TokenDevices.FirstOrDefault(x => x.Token ==token);
                if (tokenD != null)
                {
                    return false;
                }
                TokenDevice mToken = new TokenDevice();
                mToken.Token = token;
                context.TokenDevices.InsertOnSubmit(mToken);
                context.SubmitChanges();
                return true;
            }
            catch { }
            return false;
        }


    }
}
