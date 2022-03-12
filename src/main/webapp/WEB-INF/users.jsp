<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Users</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    </head>
    <body class="d-flex justify-content-around">
        <div >
            <h1>Add user</h1>
            <form action="user" method="POST">
                <input type="hidden" name="action" value="add">
                <div>
                    <input type="text" name="newUserEmail" id="newUserEmail" placeholder="Email" required>
                </div>
                <div>
                    <input type="text" name="newUserFirstName" id="newUserFirstName" placeholder="First name" required>
                </div>
                <div>
                    <input type="text" name="newUserLastName" id="newUserLastName" placeholder="Last name" required>
                </div>
                <div>
                    <input type="password" name="newUserPassword" id="newUserPassword" placeholder="Password" required>
                </div>
                <div >
                    <select name="newRole" id="newRole" required>
                        <option selected value="0">Choose your role</option>
                        <option value="1">System Admin</option>
                        <option value="2">Regular User</option>
                        <option value="3">Company Admin</option>
                    </select>
                </div>
                <div >
                    <button type="submit">Add</button>
                </div>
                
            </form>
        </div>
        
        <div>
            
            <h1>User management system</h1>
            <form action="user" method="POST">
                <input type="hidden" name="action" value="delete">
                <table class="table">
                    <thead>
                        <tr>
                            <th>E-mail</th>
                            <th>First name</th>
                            <th>Last name</th>
                            <th>Role</th>
                            <th>Active</th>
                            <th>Edit</th>
                            <th>Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="user" items="${users}">
                            <tr>
                                <td>${user.email}</td>
                                <td>${user.firstName}</td>
                                <td>${user.lastName}</td>
                                <td>${user.role.roleName}</td>
                                <td>${user.active ? "Y": "N"}</td>
                                <td><a href="#">edit</a></td>
                                <td><a href="#">delete</a></td>
                            </tr>    
                        </c:forEach>
                    </tbody>
                </table>
            </form>
        </div>
        
    </body>
</html>
