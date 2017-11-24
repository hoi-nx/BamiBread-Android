using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace MyWebAPIBamiBread.Controllers
{
    public class CategoryController : ApiController
    {
        [HttpGet]
        public Category GetCategory(int id)
        {
            BamiBreadDataContext context = new BamiBreadDataContext();
            Category c = context.Categories.FirstOrDefault(x => x.ID == id);
            if (c != null)
            {
                c.FastFoods.Clear();
            }
            return c;
        }

        [HttpDelete]

        public bool delete(int maDM)
        {
            try
            {
                BamiBreadDataContext context = new BamiBreadDataContext();
                Category c = context.Categories.FirstOrDefault(x => x.ID == maDM);
                if (c != null)
                {
                    if (c.FastFoods.Count() > 0) return false;
                    context.Categories.DeleteOnSubmit(c);
                    context.SubmitChanges();
                    return true;
                }
            }
            catch { }
            return false;
        }
    }
}
