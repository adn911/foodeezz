<%--
  Created by IntelliJ IDEA.
  User: bakhtiar.galib
  Date: 3/10/15
  Time: 11:30 AM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="header.jsp" %>

<div class="container-fluid">

    <div class="row">

        <div class="col-md-6 col-md-offset-3" style="margin-top: 50px;">

            <c:if test="${!empty param.success}">

                <c:if test="${param.success eq 1}">
                    <div class="alert alert-success" role="alert">Account signup successful.</div>
                </c:if>

            </c:if>

            <c:if test="${!empty param.error}">

                <div class="alert alert-danger" role="alert">

                    <c:if test="${param.error eq 0}">
                        Account signup failed.
                    </c:if>
                    <c:if test="${param.error eq 1}">
                        Username already exists
                    </c:if>
                    <c:if test="${param.error eq 2}">
                        Email already exists
                    </c:if>

                </div>

            </c:if>

            <div class="panel panel-info">

                <div class="panel-heading">
                    <h3 class="panel-title text-center">Signup</h3>
                </div>

                <div class="panel-body">

                    <sf:form action="/Foodeezz/signup" method="POST" commandName="newUser"
                             enctype="multipart/form-data">

                        <input id="role" name="userType" class="form-control" type="hidden" value="ROLE_USER"/> <br>

                        <%--<label>Username</label><br>--%>
                        <sf:errors path="username" cssClass="error"/><br>
                        <input id="username" name="username" class="form-control" placeholder="Enter Username"
                               type="text" value="${newUser.username}"/> <br>

                        <%--<label>Email</label><br>--%>
                        <sf:errors path="email" cssClass="error"/><br>
                        <input id="email" name="email" class="form-control" placeholder="Enter Email" type="text"
                               value="${newUser.email}"/><br>

                        <%--<label>Password</label><br>--%>
                        <sf:errors path="password" cssClass="error"/><br>
                        <input id="password" name="password" class="form-control" placeholder="Enter Password"
                               type="password"/><br>

                        <input type="submit" class="btn btn-default form-control" name="register"
                               value="Signup"/><br><br>

                        <div class="text-center">
                            Go to <a href="login">Login</a> page<br><br>
                        </div>


                    </sf:form>

                </div>

            </div>

        </div>

    </div>

</div>

<%@include file="footer.jsp" %>
