<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title><sitemesh:write property='title' /></title>

  <!-- Custom fonts for this template-->
  <link href="/static/bootstrap/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="/static/bootstrap/css/sb-admin-2.min.css" rel="stylesheet">
  <link href="/static/bootstrap/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
  
  <!-- Bootstrap core JavaScript-->
  <script src="/static/bootstrap/vendor/jquery/jquery.min.js"></script>
  <script src="/static/bootstrap/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Core plugin JavaScript-->
  <script src="/static/bootstrap/vendor/jquery-easing/jquery.easing.min.js"></script>

  <!-- Custom scripts for all pages-->
  <script src="/static/bootstrap/js/sb-admin-2.min.js"></script>

  <!-- Page level plugins -->
  <!-- 
  <script src="/static/bootstrap/vendor/chart.js/Chart.min.js"></script>
  -->
   
  <!-- Page level custom scripts -->
  <!-- 
  <script src="/static/bootstrap/js/demo/chart-area-demo.js"></script>
  <script src="/static/bootstrap/js/demo/chart-pie-demo.js"></script>
  <script src="/static/bootstrap/js/demo/datatables-demo.js"></script>
  -->
  
  <!-- handlebars -->
  <script src="/static/js/handlebars-v4.7.6.js"></script>
  
  <!-- Custom common js -->
  <script src="/static/js/common/search.js?v=202007022345"></script>
  
  <sitemesh:write property='head' />

</head>

<body id="page-top">

  <!-- Page Wrapper -->
  <div id="wrapper">

    <!-- Sidebar -->
  	<%@ include file="/WEB-INF/sitemesh/layout/layout_sidebar.jsp" %>
    <!-- End of Sidebar -->
	
    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

      <!-- Main Content -->
      <div id="content">

        <!-- Topbar -->
		<%@ include file="/WEB-INF/sitemesh/layout/layout_topbar.jsp" %>
        <!-- End of Topbar -->

        <!-- Begin Page Content -->
        <div class="container-fluid">

          <!-- Page Heading -->
		  <%@ include file="/WEB-INF/sitemesh/layout/layout_pageheading.jsp" %>
		  <!-- End of Page Heading -->

          <!-- Content -->
          <sitemesh:write property='body'/>
          <!-- Content -->
		
		</div>
        <!-- /.container-fluid -->

      </div>
      <!-- End of Main Content -->

      <!-- Footer -->
	  <%@ include file="/WEB-INF/sitemesh/layout/layout_footer.jsp" %>
      <!-- End of Footer -->

    </div>
    <!-- End of Content Wrapper -->

  </div>
  <!-- End of Page Wrapper -->

  <!-- Scroll to Top Button-->
  <a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
  </a>

  <!-- Logout Modal-->
  <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">×</span>
          </button>
        </div>
        <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
        <div class="modal-footer">
          <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
          <a class="btn btn-primary" href="login.html">Logout</a>
        </div>
      </div>
    </div>
  </div>


</body>

<script>
	$(document).ready(function() {
		var msg = '${msg}';

		if (msg) {
			alert(msg);
		}
	});
</script>

</html>
