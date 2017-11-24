using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace MyWebAPIBamiBread.Controllers
{
    public class IntroduceController : ApiController
    {
        [HttpGet]
        public List<Introduce> getListIntroduce()
        {
            try
            {
                BamiBreadDataContext context = new BamiBreadDataContext();
                List<Introduce> list = context.Introduces.ToList();
                return list;
            }
            catch { }
            return null;
           
        }
    }
}
