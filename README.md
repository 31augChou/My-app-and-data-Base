<h1>Driver App management</h1>
<p>The purpose of this web is to track down driver but only admin or employee can access to their account after they register or sign in their account</p>
<h2>Features uses</h2>
<p>Authentication — Register, login, and logout with Spring Security
Dashboard — Overview stats (total drivers, active, licensed, with email)
Driver Management — Add, edit, and delete drivers
Driver Table — View all drivers with full details
Responsive — Mobile-friendly layout with Bootstrap 5
Modern UI — Clean design with Bootstrap Icons</p>
<h2>Tool uses</h2>
<p>For project I used gradle and in there I used dependencies such as:<br>
<p>Spring web, Spring security, Thymeleaf, JDBC API, Spring data JPA, Postgres SQL Driver, SpringBoot Actuator</p>
</p>
<h2>Link to the website</h2>
<a href= "https://my-app-and-data-base-production.up.railway.app/">Website
<h2>Data Structure figure</h2>
<p> For Drivers<br>
@Table(name = "drivers")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String licenseNumber;
    private String phone;
    private String email;
</p>
<p>For Users<br>
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String role = "USER";

</p>
<h2>How to deploy on railway</h2>
<p>First, Deploy postgres database and My website.<br> Next copy variable from postgres and pasted it to springboot application in railway <br>
After that change your application properties example localhost to ${PGHOST} and wait for railway to deploy your springboot<br>
If there any erros maybe because of the difference environment or version, sometimes we need to build a file so that your application can be match with the railway environment.
Last, generate domain and you will have your domain and you can check it or test it.
</p>
<h2>AI DECLARATION</h2>
<p>For the AI Agent I used is Claude Ai <br>
Why? because I think this Ai is good for developer<br>
For the prompt I didn't ask it much only building front-end and design some style sheet for me, since I don't know much about css<br>
<img width="1100" height="893" alt="Screenshot 2026-03-09 160703" src="https://github.com/user-attachments/assets/c0a80b0b-6c24-43e5-ae61-4dd058110d37" />
 
</p>
