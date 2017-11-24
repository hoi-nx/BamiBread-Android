using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace MyWebAPIBamiBread.Controllers
{
    public class UserController : ApiController
    {
        [HttpGet]
        public List<User> GetAllUser()
        {
            BamiBreadDataContext context = new BamiBreadDataContext();
            List<User> listUser = context.Users.ToList();
            return listUser;
        }
        [HttpGet]
        public User getUser(int id)
        {
            BamiBreadDataContext context = new BamiBreadDataContext();
            User user = context.Users.FirstOrDefault(x => x.ID == id);
            return user;
        }
        [HttpGet]
        public bool getUserName(String username)
        {
            BamiBreadDataContext context = new BamiBreadDataContext();
            User user = context.Users.FirstOrDefault(x => x.Username==username);
            if (user != null)
            {
                return true;
            }
            return false;
        }
     

        [HttpGet]
        public bool checkUserNamePassword(String username,String password)
        {
            try
            {
                BamiBreadDataContext context = new BamiBreadDataContext();
                User user = context.Users.FirstOrDefault(x => x.Username == username&&x.Password==password);
                if (user != null)
                {
                    return true;
                }
            }
            catch { }
            return false;

        }

        [HttpPost]
        public bool saveUser(String username,String password,String name)
        {
            try
            {
                BamiBreadDataContext context = new BamiBreadDataContext();
                User user = new User();
                user.Username = username;
                user.Password = password;
                user.NameUser = name;
                context.Users.InsertOnSubmit(user);
                context.SubmitChanges();
                return true;
            }
            catch{ }
            return false;
        }
        [HttpPut]
        public bool updateUser(String username,String password,String name)
        {
            try
            {
                BamiBreadDataContext context = new BamiBreadDataContext();
                User user = context.Users.FirstOrDefault(x=>x.Username==username);
                if (user != null)
                {
                    user.Password = password;
                    user.NameUser = name;
                    context.SubmitChanges();
                    return true;
                }
               
               
            }
            catch { }
            return false;
        }

        [HttpDelete]
        public bool delete(String username,String password)
        {
            try
            {
                BamiBreadDataContext context = new BamiBreadDataContext();
                User user = context.Users.FirstOrDefault(x => x.Username == username && x.Password==password);
                if (user != null)
                {
                    context.Users.DeleteOnSubmit(user);
                    context.SubmitChanges();
                    return true;
                }


            }
            catch { }
            return false;
        }
    }
}
