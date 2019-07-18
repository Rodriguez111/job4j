<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ru.job4j.crudservlet.Pages" %>
<%@ page import="ru.job4j.crudservlet.User" %>
<%@ page import="ru.job4j.crudservlet.logic.ValidateService" %>


<html>
<head>
    <title>Users information</title>
    <h2>Users information</h2>
</head>
<body>

<style type='text/css'>
    .block1 {
        width: 300px;
        background: #ccc;
        padding: 5px;
        border: solid 1px grey;
        float: left;
    }
</style>


<table>
    <% for (User eachUser : ValidateService.getInstance().findAll()) {%>
    <tr>
        <td>
            <div class='block1'>
                <table>
                    <tr><b>User info:</b></tr>
                    <tr>
                        <td><label>Name: </label></td>
                        <td><label><%=eachUser.getName()%>
                        </label></td>
                    </tr>
                    <tr>
                        <td><label>Login: </label></td>
                        <td><label><%=eachUser.getLogin()%>
                        </label></td>
                    </tr>
                    <tr>
                        <td><label>E-mail: </label></td>
                        <td><label><%=eachUser.getEmail()%>
                        </label></td>
                    </tr>
                    <tr>
                        <td><label>Create date: </label></td>
                        <td><label><%=eachUser.getCreateDate()%>
                        </label></td>
                    </tr>
                </table>
                <table>
                    <tr>
                        <td>
                            <form method='get' action=<%=Pages.UPDATE.page%>>
                                <input type='hidden' name='id' value='<%=eachUser.getId()%>' />
                                <input type='hidden' name='name' value='<%=eachUser.getName()%>'/>
                                <input type='hidden' name='login' value='<%=eachUser.getLogin()%>' />
                                <input type='hidden' name='email' value='<%=eachUser.getEmail()%>' />
                                <input type='submit' value='UPDATE'>
                            </form>
                        </td>
                        <td>
                            <form method='get' action=<%=Pages.DELETE.page%>>
                                <input type='hidden' name='id' value='<%=eachUser.getId()%>' />
                                <input type='hidden' name='name' value='<%=eachUser.getName()%>' />
                                <input type='hidden' name='login' value='<%=eachUser.getLogin()%>' />
                                <input type='hidden' name='email' value='<%=eachUser.getEmail()%>' />
                                <input type='hidden' name='createDate' value='<%=eachUser.getCreateDate()%>' />
                                <input type='submit' value='DELETE'>
                            </form>
                        </td>
                    </tr>
                </table>
            </div>
        </td>
    </tr>
    <% } %>

    <tr>
        <td>
            <form method='get' action=<%=Pages.ADD.page%>>
                <input type='submit' value='ADD NEW USER'>
            </form>
        </td>
    </tr>
</table>


</body>
</html>
