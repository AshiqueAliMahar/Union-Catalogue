<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<form action="add-bk-excel" method="post" enctype="multipart/form-data">
	<label>Import Excel</label>	
	<div class="row">
		<div class="col-lg-6">
			<div class="input-group">

				<input type="file" class="form-control" name="bookFile"> <span
					class="input-group-btn">
					<button class="btn btn-secondary" type="submit">Go!</button>
				</span>
			</div>
		</div>
	</div>
</form>