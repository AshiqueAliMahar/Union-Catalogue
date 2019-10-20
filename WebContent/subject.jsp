<%@page import="org.apache.tomcat.util.codec.binary.Base64"%>
<%@page import="model.Subject"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<div>
    <h3 class="h3 text-center bg-dark text-info p-3">Subject Management</h3>
    <button class="btn btn-success btn-block mb-2 mt-2" data-toggle="modal"
            data-target="#addCollege">Add Subject</button>
    <!-- Modal -->
    <div class="modal fade" id="addCollege" tabindex="-1" role="dialog"
         aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Add Subject</h5>
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <form method="post" action=addSubject>
                    <div class="modal-body">
                        <div class="form-row">
                            <div class="form-group">
                                <label for="addcisbn">Title:</label> 
                                <input class="form-control" id="addcisbn" name="title">
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group">
                                <label for="addctitle">Call No:</label> 
                                <input class="form-control" id="addctitle" name="callNo">
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary"
                                data-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary">Save
                            changes</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="alert alert-warning" role="alert">${param.msg}</div>
    <%
    	int i=0;
    	List<Subject> subjects=(List<Subject>)request.getAttribute("subjects");
    	for(Subject subject:subjects){
    		i++;
    %>
    <div style="background-color: #f7ecb5; border-radius: 10px">
        <table class="table table-responsive-md">

            <tr class="">
                <td class="p-2">Subject Title:</td>
                <td class="p-2"><%=subject.getTitle() %></td>
            </tr>
            <tr class="">
                <td class="p-2">Call No:</td>
                <td class="p-2"><%=subject.getCallNo() %></td>
                </tr>
                <tr>
                	<td colspan="2">	
	                    <div class="btn-group">
	                        <button class="btn btn-success" data-toggle="modal" data-target="#<%=i%>">Edit</button>
	                    </div>
	                    <div>
	                        <!-- Modal -->
	                        <div class="modal fade" id="<%=i%>" tabindex="-1"
	                             role="dialog" aria-labelledby="exampleModalLabel"
	                             aria-hidden="true">
	                            <div class="modal-dialog" role="document">
	                                <div class="modal-content">
	                                    <div class="modal-header">
	                                        <h5 class="modal-title" id="m">Edit <%=subject.getTitle() %></h5>
	                                        <button type="button" class="close" data-dismiss="modal"
	                                                aria-label="Close">
	                                            <span aria-hidden="true">&times;</span>
	                                        </button>
	                                    </div>
	                                    <form method="post" action="updateSubject">
	                                        <div class="modal-body">
	                                            <div class="form-row">
	                                                <div class="form-group">
	                                                    <label for="editcemail">Call No:</label> <input
	                                                        class="form-control" id="editcemail"
	                                                        value="<%=subject.getCallNo() %>" name="callNo">
	                                                </div>
	                                            </div>
	                                        </div>
	                                        <div class="modal-footer">
	                                            <button type="button" class="btn btn-secondary"
	                                                    data-dismiss="modal">Close</button>
	                                            <button type="submit" name="title" value="<%=subject.getTitle() %>" class="btn btn-primary">Save changes</button>
	                                        </div>
	                                    </form>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
                </td>
            </tr>
        </table>
    </div>
    <%}%>
</div>