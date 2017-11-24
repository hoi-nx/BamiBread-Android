using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace MyWebAPIBamiBread.Controllers
{
    public class FastFoodController : ApiController
    {
        [HttpGet]
        public List<FastFood> getAllFastFood()
        {
            BamiBreadDataContext context = new BamiBreadDataContext();
            List<FastFood> listFastFood = context.FastFoods.ToList();
            foreach(FastFood ff in listFastFood)
            {
                ff.Category = null;
            }
            return listFastFood;
        }

        [HttpGet]
        public FastFood GetFastFood(int id)
        {
            BamiBreadDataContext context = new BamiBreadDataContext();
            FastFood ff = context.FastFoods.FirstOrDefault(x => x.ID == id);
            if (ff != null)
            {
                ff.Category = null;
            }
            return ff;

        }
        [HttpGet]
        public List<FastFood>getListFF(int maDM)
        {
            BamiBreadDataContext context = new BamiBreadDataContext();
            List<FastFood> listFastFood = context.FastFoods.Where(x=>x.IDCategory==maDM).ToList();
            foreach(FastFood ff in listFastFood)
            {
                ff.Category = null;
            }

            return listFastFood;
        }

        [HttpGet]
        public List<FastFood> getListFF(int a,int b)
        {
            BamiBreadDataContext context = new BamiBreadDataContext();
            List<FastFood> listFastFood = context.FastFoods.Where(x => x.Price>=a&&x.Price<=b).ToList();
            foreach (FastFood ff in listFastFood)
            {
                ff.Category = null;
            }

            return listFastFood;
        }
    }
}
