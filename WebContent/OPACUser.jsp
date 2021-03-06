<%@page import="model.BkPrsnlDtl"%>
<%@page import="org.apache.tomcat.util.codec.binary.Base64"%>
<%@page import="model.Book"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="">
	
	<jsp:include page="OPACNav.jsp" />
	<%
		List<Book> books = (List<Book>) request.getAttribute("books");
		Book detailedBook = (Book) request.getAttribute("detailedBook");
		int i = 0;
		if (books != null && books.size() > 0) {
	%>
	<table class="table table-responsive-md">
		<thead class="thead-dark">
			<tr>
				<th scope="col">#</th>
				<th scope="col">Title</th>
				<th scope="col">Author</th>
				<th scope="col">Edition</th>
				<th scope="col">ISBN No</th>
				<th scope="col">Subject</th>
				<th scope="col">Call No</th>
				<th scope="col">College</th>
				<th scope="col">Number Of Copies</th>
				<th scope="col">PDF</th>
				<th scope="col">Image</th>
			</tr>
		</thead>
		<tbody>
			<%
				for (Book book : books) {
						i++;
						String image = Base64.encodeBase64String(book.getImage());
			%>
			<tr>
				<th scope="row"><%=i%></th>
				<td><a href="bookDetail?barcode=<%=book.getBarcode()%>"><%=book.getTitle()%></a></td>
				<td><%=book.getAuthor()%></td>
				<td><%=book.getEdition()%></td>
				<td><%=book.getIsbn()%></td>
				<td><%=book.getSubject_title().getTitle()%></td>
				<td><%=book.getSubject_title().getCallNo()%></td>
				<td><%=book.getCollegeCode().getName()%></td>
				<td><%=book.getBkPrsnlDtls().size()%></td>
				<td><a href="downloadPdf?barcode=<%=book.getBarcode()%>">Download
						PDF</a></td>
				<td><img src="data:image/gif;base64,<%=image%>"
					class="img-fluid"></td>

			</tr>

			<%
				}
			%>
		</tbody>
	</table>
	<%
		} else if (detailedBook != null) {
			String image = Base64.encodeBase64String(detailedBook.getImage());
	%>
	<div class="row  ">
		<div class="col-md-3 m-auto text-center">
			<img src="data:image/gif;base64,<%=image%>" class="img-fluid">
		</div>
		<div class="col-md-9">
			<table class="table table-responsive-md">
				<tr>
					<td>Title:</td>
					<td><%=detailedBook.getTitle()%></td>
					<td>Author:</td>
					<td><%=detailedBook.getAuthor()%></td>
				</tr>

				<tr>
					<td>Edition:</td>
					<td><%=detailedBook.getEdition()%></td>
					<td>Total Copies:</td>
					<td><%=detailedBook.getBkPrsnlDtls().size()%></td>
				</tr>
				<tr>
					<td>ISBN:</td>
					<td><%=detailedBook.getIsbn()%></td>

					<td>Subject:</td>
					<td><%=detailedBook.getSubject_title().getTitle()%></td>
				</tr>
				<tr>
					<td>Call No:</td>
					<td><%=detailedBook.getSubject_title().getCallNo()%></td>
					<td>College:</td>
					<td><%=detailedBook.getCollegeCode().getName()%></td>
				</tr>
				<tr>
					<td>Volume:</td>
					<td><%=detailedBook.getVolume()%></td>
					<td>CD/DVD Available:</td>
					<td><%=detailedBook.getCdDvd()%></td>
				</tr>
				<tr>

					<td colspan="4"><a
						href="downloadPdf?barcode=<%=detailedBook.getBarcode()%>">Download
							PDF</a></td>
				</tr>
			</table>
		</div>
		<table class="table table-responsive-md">
			<thead class="thead-dark">
				<tr>
					<th scope="col">#</th>
					<th scope="col">Location</th>
					<th scope="col">Accession Number</th>

				</tr>
			</thead>
			<tbody>
				<%
					int j = 0;
						for (BkPrsnlDtl bkPrsnlDtl : detailedBook.getBkPrsnlDtls()) {
							j++;
				%>
				<tr>
					<th scope="row"><%=j%></th>
					<td><%=bkPrsnlDtl.getLocation()%></td>
					<td><%=bkPrsnlDtl.getAccessionNo()%></td>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>
	</div>
	<%
		}
	%>


	<%
		if (books == null && detailedBook == null) {
	%>
	<div class="row bg-info m-0 p-5 rounded text-center"
		style="font-size: 30px; font-family: 'American Typewriter'">
		<div class="col-md-4 ">
			Number of Colleges:<br> <span>${totalCollege}</span>
		</div>
		<div class="col-md-4">
			Total Books In All Colleges:<br> <span>${totalBooks}</span>
		</div>
		<div class="col-md-4">
			Total OPAC Visitors:<br> <span>10000</span>
		</div>
	</div>
	<%
		}
	%>
</div>