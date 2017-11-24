<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Admin.aspx.cs" Inherits="MyWebAPIBamiBread.Views.Admin" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
        <div>
            Nhập title message</div>
        <p>
            <asp:TextBox ID="txtTitle" runat="server" Height="33px" Width="645px"></asp:TextBox>
        </p>
        <p>
            Nhập Message</p>
        <asp:TextBox ID="txtMessage" runat="server" Height="190px" Width="642px"></asp:TextBox>
        <br />
        <asp:Button ID="btnSend" runat="server" OnClick="btnSend_Click" Text="Send Message" Width="649px" />
        <br />
        <br />
        Kết quả<p>
            <asp:TextBox ID="txtKQ" runat="server" Height="216px" Width="640px"></asp:TextBox>
        </p>
    </form>
</body>
</html>
