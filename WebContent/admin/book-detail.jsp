<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="">
	<div class="alert alert-warning" role="alert">${param.msg }</div>
	<form class="form-inline my-2 my-lg-0" action="search-Book"
		method="post">
		<input class="form-control mr-sm-2" type="search"
			placeholder="Enter ISBN" aria-label="Search" name="isbn">
		<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
	</form>
	<c:if test="${fn:length(books)-1>=0}">
		<c:forEach begin="0" end="${fn:length(books)-1}" step="1" var="i">
			<div>
				<%-- <div class="m-auto text-center">
					<img src="data:image/gif;base64,${bookPics.get(i) }"
						class="img-fluid">
				</div> --%>
				<div>
					<table class="table table-responsive-md">
						<tr>
							<td rowspan="3"><img
								src="data:image/gif;base64,${bookPics.get(i) }"
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
						<tr>
							<td colspan="4" class="m-auto text-center btn-group">
								<button class="btn btn-danger btn-block" data-toggle="modal"
									data-target="#${i}">Edit</button>
								<button class="btn btn-outline-secondary" data-toggle="modal"
									data-target="#addMC">Add More Copies</button>
							</td>
							<!-- Add More Copies -->
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
														<label for="ebdbillNo">Vendor:</label> <input
															class="form-control" id="ebdVendor" name="vendor"
															value=""> <input class="form-control"
															id="ebdVendor" type="hidden" name="isbn"
															value="${books.get(i).isbn }">
													</div>
													<div class="form-group col-md-6">
														<label for="ebdbillNo">No of Copies:</label> <input
															class="form-control" id="ebdbillNo" name="noOfCopies"
															value="">
													</div>
												</div>
												<div class="form-row">

													<div class="form-group col-md-6">
														<label for="ebdbillNo">Bill No:</label> <input
															class="form-control" id="ebdbillNo" name="billNo"
															value="">
													</div>
													<div class="form-group col-md-6">
														<label>Bill Date:</label> <input class="form-control"
															type="date" name="billDate" value="">
													</div>
												</div>
												<div class="form-row">
													<div class="form-group col-md-6">
														<label for="ebdlocation">Location:</label> <input
															class="form-control" name="location" value="">
													</div>
													<div class="form-group col-md-6">
														<label for="noOfCopies">Accession No From:</label> <input
															type="number" value="0" class="form-control"
															id="noOfCopies" name="accessionNoStart">
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
															class="form-control" id="editsubject" name="subjectTitle">
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
														<label for="editpublication">Publication Year:</label> <input
															class="form-control" id="editpublication"
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
															class="form-control" type="file" id="editpdf" name="pdf">
													</div>
													<div class="form-group col-md-6">
														<label for="editimage">Image:</label> <input type="file"
															class="form-control " id="editimagesrc${i}" name="image">

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
														showImage(src, target);
													</script>
												</div>
											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-secondary"
													data-dismiss="modal">Close</button>
												<button type="submit" class="btn btn-primary" name="barcode"
													value="${books.get(i).barcode }">Save changes</button>
											</div>
										</form>
									</div>
								</div>
							</div>

						</tr>
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
									<td scope="col">
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
																		<input type="hidden" name="bkBarcode"
																			value="${books.get(i).barcode }">
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
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
			</div>
		</c:forEach>
	</c:if>
</div>