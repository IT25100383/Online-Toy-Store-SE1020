<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en" style="scroll-behavior: smooth;">

<head>

    <title>
        ${param.pageTitle != null ? param.pageTitle : 'ToyVerse'}
    </title>

    <jsp:include page="/WEB-INF/views/partials/metadata.jsp" />

    <%
        String uri = request.getRequestURI();

        // RENAME: isAdmin to isSystemAdminPath to avoid duplication errors
        boolean isSystemAdminPath =
                uri.contains("/admin")
                        || uri.contains("/category")
                        || uri.contains("/order");
    %>

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Fredoka:wght@500;600&family=Inter:wght@400;500;600;700;800&display=swap" rel="stylesheet">

    <style>
        :root {
        <% if (isSystemAdminPath) { %>
            --primary: #ff9f43;
            --secondary: #feca57;
            --bg-body: #111827;
            --bg-secondary: #1f2937;
            --card-bg: #273549;
            --text-main: #f9fafb;
            --text-muted: #cbd5e1;
            --border-color: rgba(255,255,255,0.08);
            --font-family: 'Inter', sans-serif;
            --shadow-soft: 0 10px 30px rgba(0,0,0,0.35);
        <% } else { %>
            --primary: #ff6b35;
            --secondary: #f7b731;
            --bg-body: #fffaf5;
            --bg-secondary: #ffffff;
            --card-bg: #ffffff;
            --text-main: #1f2937;
            --text-muted: #6b7280;
            --border-color: rgba(0,0,0,0.06);
            --font-family: 'Inter', sans-serif;
            --shadow-soft: 0 10px 25px rgba(0,0,0,0.08);
        <% } %>
        }

        * { box-sizing: border-box; }

        body {
            background-color: var(--bg-body) !important;
            color: var(--text-main) !important;
            font-family: var(--font-family) !important;
            line-height: 1.6;
            letter-spacing: 0.2px;
            margin: 0;
            padding: 0;
            overflow-x: hidden;
            -webkit-font-smoothing: antialiased;
        }

        main {
            animation: fadeInUp 0.55s cubic-bezier(0.22,1,0.36,1) forwards;
            opacity: 0;
        }

        @keyframes fadeInUp {
            from { opacity: 0; transform: translateY(18px); }
            to { opacity: 1; transform: translateY(0); }
        }

        h1, h2, h3, .navbar-brand {
            font-family: 'Fredoka', sans-serif !important;
            font-weight: 600;
            letter-spacing: 0.3px;
        }

        h1 { line-height: 1.1; }
        p { color: var(--text-muted); }
        .text-accent { color: var(--primary) !important; }

        .btn-premium {
            background: linear-gradient(135deg, var(--primary), var(--secondary)) !important;
            border: none !important;
            color: white !important;
            border-radius: 999px !important;
            padding: 0.9rem 2rem;
            font-weight: 700;
            transition: transform 0.2s ease, box-shadow 0.2s ease, filter 0.2s ease;
            box-shadow: 0 10px 25px rgba(255,107,53,0.25);
        }

        .btn-premium:hover {
            transform: translateY(-3px);
            filter: brightness(1.05);
            box-shadow: 0 14px 35px rgba(255,107,53,0.32);
        }

        .premium-card, .card {
            border: 1px solid var(--border-color) !important;
            border-radius: 22px !important;
            background: var(--card-bg) !important;
            color: var(--text-main) !important;
            transition: transform 0.25s ease, box-shadow 0.25s ease;
            overflow: hidden;
            box-shadow: var(--shadow-soft);
        }

        .premium-card:hover, .card:hover {
            transform: translateY(-6px);
            box-shadow: 0 18px 40px rgba(0,0,0,0.18);
        }

        .form-control {
            border-radius: 14px !important;
            padding: 0.9rem 1rem;
            border: 1px solid var(--border-color);
            background: <% if (isSystemAdminPath) { %> #111827 <% } else { %> #ffffff <% } %>;
            color: var(--text-main);
        }

        .form-control:focus {
            border-color: var(--primary);
            box-shadow: 0 0 0 0.2rem rgba(255,107,53,0.15);
            outline: none;
        }

        .navbar { backdrop-filter: blur(12px); border-bottom: 1px solid var(--border-color); }
        .nav-link { transition: opacity 0.2s ease, transform 0.2s ease; }
        .nav-link:hover { opacity: 0.85; transform: translateY(-1px); }
        .section-spacing { padding-top: 5rem; padding-bottom: 5rem; }
        .glass-panel { background: rgba(255,255,255,0.05); backdrop-filter: blur(10px); border: 1px solid rgba(255,255,255,0.08); }
    </style>
</head>
<body>