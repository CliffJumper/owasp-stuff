public class BankManagerLoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            

            String query = request.getQueryString();

            if (query.contains("url")) {

                String url = request.getParameter("url");       

                response.sendRedirect(url);

            }

            // Get username and password from login page request

            String username = request.getParameter("username");

            String password = request.getParameter("password");

            // Authenticate user

            BankManager bankMgr = new BankManager();

            boolean isAdmin = bankMgr.verifyAdmin(password);

            // If user is authenticated then go to successful login page

            if (isAdmin) {

                request.setAttribute("login", new String("Login Successful."));

                getServletContext().getRequestDispatcher("/BankManagerServiceLoggedIn.jsp"). forward(request, response);

            }

            else {

                

                // Otherwise, raise failed login exception and output unsuccessful login message to error page

                throw new FailedLoginException("Failed Login for user " + username + " with password " + password);  

            }

        } catch (FailedLoginException ex) {

            

            // output failed login message to error page

            request.setAttribute("error", new String("Login Error"));

            request.setAttribute("message", "Error occured login in.");

            getServletContext().getRequestDispatcher("/ErrorPage.jsp").forward(request, response);

        }

    }


    private boolean verifyAdmin(String password) {

        if (strcmp(password,"68af404b513073584c4b6f22b6c63e6b")) {               

        return(true);

        }

        return(false);

        }

}
