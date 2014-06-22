<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <script type="text/javascript" src="/js/jquery-1.11.1.min.js"></script>
    </head>
    <body>
        <h1>Login</h1>
        <form method="post">
            <table class="form">
                <tr>
                    <td class="caption">Username:</td>
                    <td class="data">
                        <input id="username" name="username"/>
                    </td>
                </tr>
                <tr>
                    <td class="caption">Password:</td>
                    <td class="data">
                        <input type="password" name="password"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" class="buttons">
                        <input type="submit" name="ok" value="Login"/>
                    </td>
                </tr>
            </table>            
        </form>
        <script type="text/javascript">
            $("#username").focus();
        </script>
    </body>
</html>
