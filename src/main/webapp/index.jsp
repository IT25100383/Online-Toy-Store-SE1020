<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/WEB-INF/views/partials/header.jsp">
    <jsp:param name="pageTitle" value="ToyVerse - Where Fun Lives!" />
</jsp:include>

<jsp:include page="/WEB-INF/views/partials/navbar.jsp" />

<main>

    <!-- HERO CAROUSEL -->
    <div id="toyHeroCarousel"
         class="carousel slide carousel-fade shadow-lg mb-5"
         data-bs-ride="carousel">

        <div class="carousel-indicators">
            <button type="button" data-bs-target="#toyHeroCarousel" data-bs-slide-to="0" class="active"></button>
            <button type="button" data-bs-target="#toyHeroCarousel" data-bs-slide-to="1"></button>
            <button type="button" data-bs-target="#toyHeroCarousel" data-bs-slide-to="2"></button>
        </div>

        <div class="carousel-inner" style="height:650px;">
            <div class="carousel-item active">
                <img src="${pageContext.request.contextPath}/images/banners/banner1.jpeg"
                     class="d-block w-100" alt="Premium Toys"
                     style="height:650px; object-fit:cover; filter:brightness(0.65);">
                <div class="carousel-caption d-none d-md-block text-start mb-5 hero-overlay">
                    <span class="badge bg-warning text-dark px-3 py-2 mb-3">Premium Collectibles</span>
                    <h1 class="display-3 fw-bold text-white mb-4">Amazing Toys for <br><span class="text-warning">Big Imaginations!</span></h1>
                    <p class="lead text-light mb-4">Explore premium building sets, RC vehicles, collectibles, and unforgettable hobby experiences.</p>
                    <a href="${pageContext.request.contextPath}/toy?action=view" class="btn btn-premium btn-lg px-5">Shop Now</a>
                </div>
            </div>

            <div class="carousel-item">
                <img src="${pageContext.request.contextPath}/images/banners/banner2.jpeg"
                     class="d-block w-100" alt="Toy Collection"
                     style="height:650px; object-fit:cover; filter:brightness(0.65);">
                <div class="carousel-caption d-none d-md-block text-start mb-5 hero-overlay">
                    <span class="badge bg-warning text-dark px-3 py-2 mb-3">New Arrivals</span>
                    <h1 class="display-3 fw-bold text-white mb-4">Unbox Your <br><span class="text-warning">Next Adventure</span></h1>
                    <p class="lead text-light mb-4">Discover high-quality collectibles, display figures, and immersive toy experiences.</p>
                    <a href="${pageContext.request.contextPath}/toy?action=view" class="btn btn-premium btn-lg px-5">View Collections</a>
                </div>
            </div>

            <div class="carousel-item">
                <img src="${pageContext.request.contextPath}/images/banners/banner3.jpeg"
                     class="d-block w-100" alt="Collector Toys"
                     style="height:650px; object-fit:cover; filter:brightness(0.65);">
                <div class="carousel-caption d-none d-md-block text-start mb-5 hero-overlay">
                    <span class="badge bg-warning text-dark px-3 py-2 mb-3">Collector Favorites</span>
                    <h1 class="display-3 fw-bold text-white mb-4">Built for Collectors <br><span class="text-warning">Designed for Fun</span></h1>
                    <p class="lead text-light mb-4">Premium figures, racing kits, plush collections, and hobby-grade experiences.</p>
                    <a href="${pageContext.request.contextPath}/toy?action=view" class="btn btn-premium btn-lg px-5">Explore Store</a>
                </div>
            </div>
        </div>

        <button class="carousel-control-prev" type="button" data-bs-target="#toyHeroCarousel" data-bs-slide="prev">
            <span class="carousel-control-prev-icon"></span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#toyHeroCarousel" data-bs-slide="next">
            <span class="carousel-control-next-icon"></span>
        </button>
    </div>

    <!-- FEATURED COLLECTIONS -->
    <section class="container py-5">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2 class="fw-bold">Featured Collections</h2>
            <a href="${pageContext.request.contextPath}/toy?action=view" class="btn btn-outline-warning rounded-pill px-4">Explore All</a>
        </div>

        <div class="row g-4">
            <!-- SOFT -->
            <div class="col-md-6 col-lg-4">
                <a href="${pageContext.request.contextPath}/toy?action=view&category=Soft" class="text-decoration-none text-dark">
                    <div class="card border-0 shadow-lg overflow-hidden h-100">
                        <img src="${pageContext.request.contextPath}/images/collections/soft.png" class="card-img-top" style="height:220px; object-fit:cover;" alt="Soft Toys">
                        <div class="card-body">
                            <h5 class="fw-bold">Soft</h5>
                            <p class="text-secondary">Plush companions, collectible soft toys, and comfort favorites.</p>
                        </div>
                    </div>
                </a>
            </div>

            <!-- CREATIVE -->
            <div class="col-md-6 col-lg-4">
                <a href="${pageContext.request.contextPath}/toy?action=view&category=Creative" class="text-decoration-none text-dark">
                    <div class="card border-0 shadow-lg overflow-hidden h-100">
                        <img src="${pageContext.request.contextPath}/images/collections/creative.png" class="card-img-top" style="height:220px; object-fit:cover;" alt="Creative Toys">
                        <div class="card-body">
                            <h5 class="fw-bold">Creative</h5>
                            <p class="text-secondary">Building sets, art kits, and imaginative creative experiences.</p>
                        </div>
                    </div>
                </a>
            </div>

            <!-- PUZZLES -->
            <div class="col-md-6 col-lg-4">
                <a href="${pageContext.request.contextPath}/toy?action=view&category=Puzzles" class="text-decoration-none text-dark">
                    <div class="card border-0 shadow-lg overflow-hidden h-100">
                        <img src="${pageContext.request.contextPath}/images/collections/puzzles.png" class="card-img-top" style="height:220px; object-fit:cover;" alt="Puzzle Toys">
                        <div class="card-body">
                            <h5 class="fw-bold">Puzzles</h5>
                            <p class="text-secondary">Brain teasers, logic games, and immersive puzzle challenges.</p>
                        </div>
                    </div>
                </a>
            </div>

            <!-- SPORTS -->
            <div class="col-md-6 col-lg-4">
                <a href="${pageContext.request.contextPath}/toy?action=view&category=Sports" class="text-decoration-none text-dark">
                    <div class="card border-0 shadow-lg overflow-hidden h-100">
                        <img src="${pageContext.request.contextPath}/images/collections/sports.png" class="card-img-top" style="height:220px; object-fit:cover;" alt="Sports Toys">
                        <div class="card-body">
                            <h5 class="fw-bold">Sports</h5>
                            <p class="text-secondary">RC racers, outdoor fun, active play, and sports gear.</p>
                        </div>
                    </div>
                </a>
            </div>

            <!-- BOARD GAMES -->
            <div class="col-md-6 col-lg-4">
                <a href="${pageContext.request.contextPath}/toy?action=view&category=Board Games" class="text-decoration-none text-dark">
                    <div class="card border-0 shadow-lg overflow-hidden h-100">
                        <img src="${pageContext.request.contextPath}/images/collections/board-games.png" class="card-img-top" style="height:220px; object-fit:cover;" alt="Board Games">
                        <div class="card-body">
                            <h5 class="fw-bold">Board Games</h5>
                            <p class="text-secondary">Strategy games, tabletop classics, party games, and family favorites.</p>
                        </div>
                    </div>
                </a>
            </div>
        </div>
    </section>

    <!-- WHY CHOOSE US -->
    <section class="py-5" style="background:#111827;">
        <div class="container text-center text-white">
            <h2 class="fw-bold mb-3">Why Choose ToyVerse?</h2>
            <div class="row g-4 mt-4">
                <div class="col-md-4">
                    <i class="bi bi-patch-check-fill fs-1 text-warning"></i>
                    <h5 class="mt-3 fw-bold">Premium Quality</h5>
                    <p class="text-secondary">Carefully selected toys and collectibles built for quality and durability.</p>
                </div>
                <div class="col-md-4">
                    <i class="bi bi-truck fs-1 text-warning"></i>
                    <h5 class="mt-3 fw-bold">Fast Delivery</h5>
                    <p class="text-secondary">Reliable delivery and secure packaging for every order.</p>
                </div>
                <div class="col-md-4">
                    <i class="bi bi-stars fs-1 text-warning"></i>
                    <h5 class="mt-3 fw-bold">Collector Approved</h5>
                    <p class="text-secondary">Trusted by hobbyists, gamers, and enthusiasts.</p>
                </div>
            </div>
        </div>
    </section>

    <!-- NEWSLETTER -->
    <section class="py-5 bg-dark text-white">
        <div class="container text-center">
            <h2 class="fw-bold mb-3">Stay Updated on New Arrivals</h2>
            <p class="text-secondary mb-4">Get updates about premium collectibles, featured toys, and limited edition releases.</p>
            <div class="row justify-content-center">
                <div class="col-md-6">
                    <div class="input-group input-group-lg">
                        <input type="email" class="form-control" placeholder="Enter your email">
                        <button class="btn btn-warning">Subscribe</button>
                    </div>
                </div>
            </div>
        </div>
    </section>

</main>

<jsp:include page="/WEB-INF/views/partials/footer.jsp" />