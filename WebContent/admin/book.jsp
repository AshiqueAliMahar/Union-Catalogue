<%@page import="model.Book"%>
<%@page import="org.apache.tomcat.util.codec.binary.Base64"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!--Book-->
<div class=" ">

	<!-- <h3 class="h3 text-center bg-dark text-info p-3">Book Management</h3>
	<div class="btn-group" role="group" class="col-md-9">
		<button class="btn btn-danger" data-toggle="modal"
			data-target="#addBook">Add Book</button>
		<button class="btn btn-warning" data-toggle="modal"
			data-target="#addBook">Import Excel</button>
		<button class="btn btn-info" data-toggle="modal"
			data-target="#addBook">Download Excel Pattern</button>
		<button class="btn btn-outline-secondary" data-toggle="modal"
			data-target="#addMC">Add More Copies</button>
	</div> -->
		<%-- <!-- Add more copies Modal -->
		<div class="modal fade" id="addMC" tabindex="-1" role="dialog"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<form action="addMCopies" method="post">
						<div class="modal-header">
							<h5 class="modal-title" id="bkdtlE">Add More Copies</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div> 
						<div class="modal-body">
							<div class="form-row">
								<div class="form-group col-md-6">
									<label for="ebdbillNo">ISBN:</label> <input
										class="form-control" id="ebdVendor" name="isbn" value="">
								</div>
								<div class="form-group col-md-6">
									<label for="ebdbillNo">No of Copies:</label> <input
										class="form-control" id="ebdbillNo" name="noOfCopies" value="">
								</div>
							</div>
							<div class="form-row">
								<div class="form-group col-md-6">
									<label for="ebdbillNo">Vendor:</label> <input
										class="form-control" id="ebdVendor" name="vendor" value="">
								</div>
								<div class="form-group col-md-6">
									<label for="ebdbillNo">Bill No:</label> <input
										class="form-control" id="ebdbillNo" name="billNo" value="">
								</div>
							</div>
							<div class="form-row">

								<div class="form-group col-md-6">
									<label>Bill Date:</label> <input class="form-control"
										type="date" name="billDate" value="">
								</div>
								<div class="form-group col-md-6">
									<label for="ebdlocation">Location:</label> <input
										class="form-control" name="location" value="">
								</div>
							</div>
							<div class="form-row">
								<div class="form-group col-md-12">
									<label for="noOfCopies">Accession No From:</label> <input
										type="number" value="0" class="form-control" id="noOfCopies"
										name="accessionNoStart">
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">Close</button>
							<button type="submit" class="btn btn-primary" name="barcode"
								value="">Save changes</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	<!-- Add Book Modal -->
	<div class="modal fade" id="addBook" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<form action="addBook" method="post" enctype="multipart/form-data">
					<div class="modal-header">
						<h5 class="modal-title" id="bk">Add Book</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<div class="form-row">
							<div class="form-group col-md-6">
								<label for="addbisbn">ISBN:</label>
								<!-- <input class="form-control" id="addbisbn" name="isbn"> -->
								<div class="input-group">
									<input type="text" name="isbn" value="${book.isbn}"
										class="form-control" placeholder="Search for ISBN"> <span
										class="input-group-btn">
										<button class="btn btn-secondary" type="submit" name="find"
											value="find">Go!</button>
									</span>
								</div>
							</div>
							<div class="form-group col-md-6">
								<label for="addbtitle">Title:</label> <input
									class="form-control" type="text" id="addbtitle" name="title"
									value="${book.title}">
							</div>
						</div>
						<div class="form-row">

							<div class="form-group col-md-6">
								<label for="edition">Edition:</label> <input
									class="form-control" id="edition" name="edition"
									value="${book.edition}">
							</div>
							<div class="form-group col-md-6">
								<label for="volume">Volume:</label> <input class="form-control"
									id="volume" name="volume" value="${book.volume }">
							</div>
						</div>
						<div class="form-row">
							<div class="form-group col-md-6">
								<label for="subject">Subject:</label> <select
									class="form-control" id="subject" name="subjectTitle">
									<c:forEach items="${subjects }" var="subject">
										<option value="${subject.title}">${subject.title}</option>
									</c:forEach>

								</select>
							</div>
							<div class="form-group col-md-6">
								<label for="publisher">Publisher:</label> <input
									class="form-control" id="publisher" name="publisher"
									value="${book.publisher }">
							</div>
						</div>
						<div class="form-row">
							<div class="form-group col-md-6">
								<label for="publication">Publication Year:</label> <input
									class="form-control" type="number" id="publication"
									name="publicationYear" value="${book.publicationYear }">
							</div>
							<div class="form-group col-md-6">
								<label for="publicationplace">Publication Place:</label> <input
									class="form-control" id="publicationplace"
									name="publicationPlace" value="${book.publicationPlace }">
							</div>
						</div>
						<div class="form-row">
							<div class="form-group col-md-6">
								<label for="pages">Pages:</label> <input class="form-control"
									id="pages" name="pages" value="${book.pages }">
							</div>
							<div class="form-group col-md-6">
								<label for="dept">Deptt:</label> <select class="form-control"
									id="dept" name="deptCode">
									<c:forEach items="${depts }" var="dept">
										<option value="${dept.code}">${dept.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-row">
							<div class="form-group col-md-6">
								<label for="billNo">Bill No:</label> <input class="form-control"
									id="billNo" name="billNo">
							</div>
							<div class="form-group col-md-6">
								<label for="billDate">Bill Date:</label> <input
									class="form-control" id="billDate" name="billDate" type="date"
									placeholder="2018-12-22">
							</div>
						</div>
						<div class="form-row">

							<div class="form-group col-md-6">
								<label for="location">Location:</label> <input
									class="form-control" id="location" name="location">
							</div>
							<div class="form-group col-md-6">
								<label for="Author">Author:</label> <input class="form-control"
									id="Author" value="${book.author}" name="author">
							</div>
						</div>
						<div class="form-row">

							<div class="form-group col-md-6">
								<label for="Vendor">Vendor(Detailed Name):</label> <input
									class="form-control" id="Vendor" name="vendor">
							</div>
							<div class="form-group col-md-6">
								<label for="noOfCopies">No of Copies:</label> <input
									type="number" value="0" class="form-control" id="noOfCopies"
									name="noOfCopies">
							</div>
						</div>
						<div class="form-row">
							<div class="form-group col-md-12">
								<label for="noOfCopies">Accession No From:</label> <input
									type="number" value="0" class="form-control" id="noOfCopies"
									name="accessionNoStart">
							</div>
						</div>
						<div class="form-row">
							<div class="form-group col-md-6">
								<label for="image">Image:</label> <input type="file"
									class="form-control " id="src" name="image">
								<hr>

							</div>
							<div class="form-group col-md-6">
								<label for="pdf">PDF file:</label> <input class="form-control"
									type="file" id="pdf" name="pdf">
							</div>
						</div>
						<div class="form-row">
							<div class="form-group col-md-6">
								<img src="data:image/gif;base64,${image }"
									class="img-fluid rounded" id="target">
							</div>
							<div id="dvd" class="form-group col-md-6">
								<label for="dvd">CD/DVD:</label> <input type="radio" name="dvd"
									value="Yes" checked="checked"><span>Yes</span> <input
									type="radio" name="dvd" value="No"><span>No</span>
							</div>
						</div>

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Close</button>
						<button type="submit" name="addBook" class="btn btn-primary">Save
							changes</button>
					</div>
				</form>
			</div>
		</div>
	</div> --%>
	<div class="alert alert-warning" role="alert">${param.msg }</div>
	
	<c:if test="${fn:length(books)-1>=0}">
		<c:forEach begin="0" end="${fn:length(books)-1}" step="1" var="i">
			<div class="rounded mb-2 border rounded">
				<%-- <div class="m-auto text-center">
					<img src="data:image/gif;base64,${bookPics.get(i) }"
						class="img-fluid">
				</div> --%>
				<div class="">
						<table class="table table-responsive-md ">
							<tr>
								<td rowspan="3"><img src="data:image/gif;base64,${bookPics.get(i) }"
						class="img-fluid"></td>
								<td>Title:</td>
								<td class="h3">${books.get(i).title }</td>
								<td>Author:</td>
								<td>${books.get(i).author }</td>
							</tr>

							<tr>
								<td>Edition:</td>
								<td>${books.get(i).edition }</td>
								<td>ISBN:</td>
								<td>${books.get(i).isbn }</td>
							</tr>
							<tr>
								<td>Subject:</td>
								<td>${books.get(i).subject.title }</td>
								<td>Call No:</td>
								<td>${books.get(i).subject.callNo}</td>
							</tr>
							<tr>

								<td>College:</td>
								<td>${books.get(i).college.name }</td>
								<td>Total Copies:</td>
								<td>${fn:length(books.get(i).bkPrsnlDtls) }</td>
							</tr>
							<tr>

								<td>Publisher:</td>
								<td>${books.get(i).publisher }</td>
								<td>Publication Year:</td>
								<td>${books.get(i).publicationYear }</td>
							</tr>
							<tr>

								<td>Publication Place:</td>
								<td>${books.get(i).publicationPlace }</td>
								<td>Pages:</td>
								<td>${books.get(i).pages }</td>
							</tr>
							<tr>

								<td>Department:</td>
								<td>${books.get(i).dept.name }</td>
								<td>CD/DVD Available:</td>
								<td>${books.get(i).cdDvd}</td>
							</tr>
							<tr>
								<td colspan="2" class=""><a
									href="downloadPdf?barcode=${books.get(i).barcode}">Download
										PDF</a></td>
								<td>Volume:</td>
								<td>${books.get(i).volume}</td>
							</tr>
							<!-- <tr> -->
								<%-- <td colspan="4" class="m-auto text-center">
									<button class="btn btn-danger btn-block" data-toggle="modal"
										data-target="#${i}">Edit</button>
									<div>
										<!-- Modal -->
										<div class="modal fade" id="${i}" tabindex="-1" role="dialog"
											aria-labelledby="exampleModalLabel" aria-hidden="true">
											<div class="modal-dialog" role="document">
												<div class="modal-content">
													<form action="updateBook" method="post"
														enctype="multipart/form-data">
														<div class="modal-header">
															<h5 class="modal-title" id="eBook">Edit Book</h5>
															<button type="button" class="close" data-dismiss="modal"
																aria-label="Close">
																<span aria-hidden="true">&times;</span>
															</button>
														</div>
														<div class="modal-body">
															<div class="form-row">
																<div class="form-group col-md-6">
																	<label for="editisbn">ISBN:</label> <input
																		class="form-control" id="editisbn"
																		value="${books.get(i).isbn }" name="isbn">
																</div>

																<div class="form-group col-md-6">
																	<label for="edittitle">Title:</label> <input
																		class="form-control" id="edittitle"
																		value="${books.get(i).title }" name="title">
																</div>
															</div>
															<div class="form-row">
																<div class="form-group col-md-6">
																	<label for="editauthor">Author:</label> <input
																		class="form-control" id="editauthor"
																		value="${books.get(i).author }" name="author">
																</div>
																<div class="form-group col-md-6">
																	<label for="editedition">Edition:</label> <input
																		class="form-control" id="editedition"
																		value="${books.get(i).edition }" name="edition">
																</div>
															</div>
															<div class="form-row">
																<div class="form-group col-md-6">
																	<label for="editsubject">Subject:</label> <select
																		class="form-control" id="editsubject"
																		name="subjectTitle">
																		<c:forEach items="${subjects }" var="subject">
																			<c:if
																				test="${subject.title == books.get(i).subject.title }">
																				<option value="${subject.title}" selected>${subject.title}</option>
																			</c:if>
																			<c:if
																				test="${subject.title != books.get(i).subject.title }">
																				<option value="${subject.title}">${subject.title}</option>
																			</c:if>

																		</c:forEach>
																	</select>
																</div>
																<div class="form-group col-md-6">
																	<label for="editpublisher">Publisher:</label> <input
																		class="form-control" id="editpublisher"
																		value="${books.get(i).publisher }" name="publisher">
																</div>
															</div>
															<div class="form-row">
																<div class="form-group col-md-6">
																	<label for="editpublication">Publication Year:</label>
																	<input class="form-control" id="editpublication"
																		name="publicationYear"
																		value="${books.get(i).publicationYear }" type="number"
																		name="publicationYear">
																</div>
																<div class="form-group col-md-6">
																	<label for="editpublicationplace">Publication
																		Place:</label> <input class="form-control"
																		id="editpublicationplace"
																		value="${books.get(i).publicationPlace }"
																		name="publicationPlace">
																</div>
															</div>
															<div class="form-row">
																<div class="form-group col-md-6">
																	<label for="editpages">Pages:</label> <input
																		class="form-control" id="editpages"
																		value="${books.get(i).pages}" name="pages">
																</div>
																<div class="form-group col-md-6">
																	<label>CD/DVD:</label>
																	<c:if test="${books.get(i).cdDvd eq 'Yes' }">
																		<input type="radio" value="Yes" checked="checked"
																			name="dvd">
																		<span>Yes</span>
																		<input type="radio" value="No" name="dvd">
																		<span>No</span>
																	</c:if>
																	<c:if test="${books.get(i).cdDvd ne 'Yes' }">
																		<input type="radio" value="Yes" name="dvd">
																		<span>Yes</span>
																		<input type="radio" value="No" checked="checked"
																			name="dvd">
																		<span>No</span>
																	</c:if>
																</div>
															</div>
															<div class="form-row">

																<div class="form-group col-md-6">
																	<label for="editdept">Department:</label> <select
																		class="form-control" id="editdept" name="deptCode">
																		<c:forEach items="${depts }" var="dept">
																			<c:if test="${dept.code == books.get(i).dept.code }">
																				<option value="${dept.code}" selected>${dept.name}</option>
																			</c:if>
																			<c:if test="${dept.code != books.get(i).dept.code }">
																				<option value="${dept.code}" selected>${dept.name}</option>
																			</c:if>
																		</c:forEach>
																	</select>
																</div>
																<div class="form-group col-md-6">
																	<label for="volume">Volume:</label> <input
																		class="form-control" id="volume" name="volume"
																		value="${books.get(i).volume }">
																</div>
															</div>
															<div class="form-row">
																<div class="form-group col-md-6">
																	<label for="editpdf">PDF file:</label> <input
																		class="form-control" type="file" id="editpdf"
																		name="pdf">
																</div>
																<div class="form-group col-md-6">
																	<label for="editimage">Image:</label> <input
																		type="file" class="form-control "
																		id="editimagesrc${i}" name="image">

																	<hr>
																</div>

															</div>
															<div class="form-row">
																<div class="form-group col-md-12">
																	<img src="data:image/gif;base64,${bookPics.get(i) }"
																		class="img-fluid rounded" id="editimageTarget${i}">
																</div>
																<script>
																	var src = document
																			.getElementById("editimagesrc${i}");
																	var target = document
																			.getElementById("editimageTarget${i}");
																	showImage(
																			src,
																			target);
																</script>
															</div>
														</div>
														<div class="modal-footer">
															<button type="button" class="btn btn-secondary"
																data-dismiss="modal">Close</button>
															<button type="submit" class="btn btn-primary"
																name="barcode" value="${books.get(i).barcode }">Save
																changes</button>
														</div>
													</form>
												</div>
											</div>
										</div>
									</div> --%>
							<!-- 	</td>
							</tr> -->
						</table>
				</div>

				<table class="table table-responsive-md">
					<thead class="thead-dark">
						<tr>
							<th scope="col">#</th>
							<th scope="col">Vendor</th>
							<th scope="col">Bill No</th>
							<th scope="col">Bill Date</th>
							<th scope="col">Accession Number</th>
							<th scope="col">Location</th>
							<th scope="col">Operation</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${fn:length(books.get(i).bkPrsnlDtls) gt 0 }">
							<c:forEach begin="0"
								end="${fn:length(books.get(i).bkPrsnlDtls)-1}" step="1" var="k">
								<tr>
									<th scope="row">${k+1}</th>
									<td scope="col">${books.get(i).bkPrsnlDtls.get(k).vendor }</td>
									<td scope="col">${books.get(i).bkPrsnlDtls.get(k).billNo }</td>
									<td scope="col">${books.get(i).bkPrsnlDtls.get(k).billDate }</td>
									<td scope="col">${books.get(i).bkPrsnlDtls.get(k).accessionNo }</td>
									<td scope="col">${books.get(i).bkPrsnlDtls.get(k).location }</td>
									<%-- <td scope="col">
										<button class="btn btn-danger btn-block" data-toggle="modal"
											data-target="#editBookDtl${books.get(i).bkPrsnlDtls.get(k).barcode }">Edit</button>
										<div>
											<!-- Modal -->
											<div class="modal fade"
												id="editBookDtl${books.get(i).bkPrsnlDtls.get(k).barcode }"
												tabindex="-1" role="dialog"
												aria-labelledby="exampleModalLabel" aria-hidden="true">
												<div class="modal-dialog" role="document">
													<div class="modal-content">
														<form action="updateBkDtl" method="post">
															<div class="modal-header">
																<h5 class="modal-title" id="bkdtlE">Edit Book
																	Detail</h5>
																<button type="button" class="close" data-dismiss="modal"
																	aria-label="Close">
																	<span aria-hidden="true">&times;</span>
																</button>
															</div>
															<div class="modal-body">
																<div class="form-row">
																	<div class="form-group col-md-6">
																		<label for="ebdbillNo">Vendor:</label> <input
																			class="form-control" id="ebdVendor" name="vendor"
																			value="${books.get(i).bkPrsnlDtls.get(k).vendor }">
																	</div>
																	<div class="form-group col-md-6">
																		<label for="ebdbillNo">Bill No:</label> <input
																			class="form-control" id="ebdbillNo" name="billNo"
																			value="${books.get(i).bkPrsnlDtls.get(k).billNo }">
																	</div>
																</div>
																<div class="form-row">

																	<div class="form-group col-md-6">
																		<label>Bill Date:</label> <input class="form-control"
																			type="date" name="billDate"
																			value="${books.get(i).bkPrsnlDtls.get(k).billDate }">
																	</div>
																	<div class="form-group col-md-6">
																		<label for="ebdlocation">Location:</label> <input
																			class="form-control" name="location"
																			value="${books.get(i).bkPrsnlDtls.get(k).location }">
																	</div>
																</div>
																<div class="form-row">
																	<div class="form-group col-md-12">
																		<label for="ebdlocation">Accession No:</label> <input
																			class="form-control" name="accessionNo" type="number"
																			value="${books.get(i).bkPrsnlDtls.get(k).accessionNo }">
																	</div>
																</div>
															</div>
															<div class="modal-footer">
																<button type="button" class="btn btn-secondary"
																	data-dismiss="modal">Close</button>
																<button type="submit" class="btn btn-primary"
																	name="barcode"
																	value="${books.get(i).bkPrsnlDtls.get(k).barcode }">Save
																	changes</button>
															</div>
														</form>
													</div>
												</div>
											</div>
										</div>
									</td>
								</tr> --%>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
			</div>
		</c:forEach>
	</c:if>
</div>