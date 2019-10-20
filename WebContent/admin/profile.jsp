<%@page import="org.apache.tomcat.util.codec.binary.Base64"%>
<%@page import="model.Librarian"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="col-md-6 col-sm-12 m-auto p-2">
        <div class="row m-auto">
            
            <img src='data:image/gif;base64,${sessionScope.pic}' class="img-fluid rounded-circle m-auto" id="target">
        </div>
        <div class="alert alert-warning" role="alert">${param.msg }</div>
        <form method="post" action="updateAdminProfile" enctype="multipart/form-data">
                <div class="form-row">
                    <div class="form-group col-md-12">
                        <label>Image:</label>
                        <input class="form-control" type="file" id="src" name="pic">
                        <script>
                            var src = document.getElementById("src");
                            var target = document.getElementById("target");
                            showImage(src, target);
                        </script>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-12">
                        <label for="addlname">Name:</label>
                        <input class="form-control" id="addlname" name="name" value="${sessionScope.name }">
                    </div>

                </div>
                <div class="form-row">
                    <div class="form-group col-md-12">
                        <label for="addlcontact">Contact:</label> <input
                            class="form-control" id="addlcontact" name="cell" value="${sessionScope.cell }">
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-12">
                        <label for="addlPassword">Password:</label>
                        <input class="form-control" id="addlPassword" name="password"  type="password" value="${sessionScope.password }">
                    </div>
                </div>
                <div class="form-row">

                    <div class="form-group col-md-12">
                        <label for="addlcPassword">Confirm Password:</label> <input
                            class="form-control" id="addlcPassword" name="cPassword"
                            type="password" value="${sessionScope.password }">
                    </div>
                </div>
                <div class="form-row">
                    <button class="btn btn-success btn-block" name="email" value="${sessionScope.email }">Save</button>
                </div>
        </form>
    </div>