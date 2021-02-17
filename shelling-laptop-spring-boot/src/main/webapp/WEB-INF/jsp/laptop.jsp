<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Static Navigation - SB Admin</title>
      
      	<link href='<c:url value='/template/admin/css/styles.css'/>' rel="stylesheet" />
       	<script src="<c:url value='/template/admin/js/all.min.js'/>"></script>
    </head>
    <body>
        <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
        	<a class="navbar-brand" href="index.html">Start Bootstrap</a>
            <button class="btn btn-link btn-sm order-1 order-lg-0" id="sidebarToggle" href="#">
            	<i class="fas fa-bars"></i>
            </button>
            
            <!-- Navbar Search-->
            <form class="d-none d-md-inline-block form-inline ml-auto mr-0 mr-md-3 my-2 my-md-0">
                <div class="input-group">
                    <input class="form-control" type="text" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2" />
                    <div class="input-group-append">
                        <button class="btn btn-primary" type="button"><i class="fas fa-search"></i></button>
                    </div>
                </div>
            </form>
            
            <!-- Navbar-->
            <ul class="navbar-nav ml-auto ml-md-0">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="userDropdown" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="userDropdown">
                        <a class="dropdown-item" href="#">Settings</a>
                        <a class="dropdown-item" href="#">Activity Log</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="login.html">Logout</a>
                    </div>
                </li>
            </ul>
        </nav>
        <div id="layoutSidenav">
            <div id="layoutSidenav_nav">
                <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                    <div class="sb-sidenav-menu">
                        <div class="nav">
                            <a class="nav-link" href="/laptoppage">
                                <div class="sb-nav-link-icon"><i class="fas fa-table"></i></div>
                                Laptops
                            </a>
							 <a class="nav-link" href="/orderpage">
                                <div class="sb-nav-link-icon"><i class="fas fa-table"></i></div>
                                Orders
                            </a>
                        </div>
                    </div>
                </nav>
            </div>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid">
                        <div class="card mb-4">
							<div class="card-header">
                                <i class="fas fa-table mr-1"></i>
                                DataTable Laptop
                            </div>
                           <div class="card-body">
                                <div class="table-responsive">
                                	<a href="/newlaptop">Create New Laptop</a>
                                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                        <thead>
								            <tr>
								            	<th>Image</th>
								                <th>Laptop ID</th>
								                <th>Brand</th>
								                <th>Name</th>
								                <th>CPU</th>
								                <th>Card Graphic</th>
								                <th>Hard Drive</th>
								                <th>RAM</th>
								                <th>Display</th>
								                <th>Weight</th>
								                <th>Color</th>
								                <th>OS</th>
								                <th>Pin</th>
								                <th>Price</th>
								                <th>Actions</th>
								            </tr>
								        </thead>
								        <!-- <tbody>
								            <tr th:each="laptop : ${laptops}">
								                <td>
								                	<img th:src="${{laptop.image}}" width="200">
								                </td>
								                <td th:text="${laptop.laptopId}">Product ID</td>
								                <td th:text="${laptop.brand}">Bran</td>
								                <td th:text="${laptop.name}">Name</td>
								                <td th:text="${laptop.cpu}">Cpu</td>
								                <td th:text="${laptop.cardGraphic}">Card Graphic</td>
								                <td th:text="${laptop.hardDrive}">Hard Drive</td>
								                <td th:text="${laptop.ram}">Ram</td>
								                <td th:text="${laptop.display}">Display</td>
								                <td th:text="${laptop.weight}">Weight</td>
								                <td th:text="${laptop.color}">Color</td>
								                <td th:text="${laptop.os}">OS</td>
								                <td th:text="${laptop.pin}">Pin</td>
								                <td th:text="${laptop.priceStr}">Price</td>
								                <td>
								                    <a th:href="@{'/editlaptop/' + ${laptop.laptopId}}">Edit</a>
								                    &nbsp;&nbsp;&nbsp;
								                    <a th:href="@{'/deletelaptop/' + ${laptop.laptopId}}">Delete</a>
								                </td>
								            </tr>
								        </tbody> -->
                                    </table>
                                </div>
                            </div>
                        </div>                       
                    </div>
                </main>
            </div>
        </div>
        <script src="<c:url value='/template/admin/js/jquery-3.5.1.min.js'/>" crossorigin="anonymous"></script>
        <script src="<c:url value='/template/admin/js/bootstrap.bundle.min.js'/>" crossorigin="anonymous"></script>
        <script src="<c:url value='/template/admin/js/scripts.js'/>"></script>
    </body>
</html>
