<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<c:set var="cl" value="${ pageContext.request.contextPath }" />
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="${ cl }/source/css/style.css">
	<title>타이틀 적기 귀찮아서 이렇게 아무말이나 적어도 괜찮을 듯</title>
</head>
<body>

	<div id="load-div" style="position: absolute; top: 0; left: 0; z-index: 9999; width: 100%; 
		height: 100%; background-color: white;"></div>

	<div id="preview-div" class="w-full mx-auto p-2 m-2"></div>

	<form id="upload-form" autocomplete="off" enctype="multipart/form-data" class="max-w-xl mx-auto m-3 px-3">
		<div class="flex items-center justify-center w-full">
			<label for="dropzone-file" class="flex flex-col items-center justify-center w-full h-64 border-2 border-gray-300 border-dashed rounded-lg cursor-pointer bg-gray-50 hover:bg-gray-100">
				<div class="flex flex-col items-center justify-center pt-5 pb-6">
					<svg class="w-8 h-8 mb-4 text-gray-500" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 20 16">
                        <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 13h3a3 3 0 0 0 0-6h-.025A5.56 5.56 0 0 0 16 6.5 5.5 5.5 0 0 0 5.207 5.021C5.137 5.017 5.071 5 5 5a4 4 0 0 0 0 8h2.167M10 15V6m0 0L8 8m2-2 2 2"></path>
                    </svg>
					<p class="text-xs text-gray-500">파일 업로드</p>
				</div>
				<input id="dropzone-file" type="file" name="file" class="hidden" onchange="previewFile()" />
			</label>
		</div><br>

		<div class="w-full">
			<p id="uploadText"></p>
			<progress id="upload-progress" value="0" max="100" class="w-full hidden"></progress>
		</div>

		<button type="button" id="upload-btn" class="px-6 py-2 bg-black text-white w-full rounded hidden">업로드</button>
	</form>

	<script src="https://cdn.tailwindcss.com"></script>
	<script src="${ cl }/source/js/script.js"></script>

</body>
</html>