using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace MyWebAPIBamiBread.Controllers
{
    public class StoreBamiController : ApiController
    {
        [HttpGet]
        public List<StoreBami> getListStore()
        {
            BamiBreadDataContext context = new BamiBreadDataContext();
            List<StoreBami> list = context.StoreBamis.ToList();
            return list;
        }
    }
}
