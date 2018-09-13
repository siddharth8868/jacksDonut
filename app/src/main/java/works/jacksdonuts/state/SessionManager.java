package works.jacksdonuts.state;

/**
 * This is a Singleton class, and keep track of session
 *
 * Created by siddharth on 6/23/18.
 */


public class SessionManager {

    private static final SessionManager sessionManager = new SessionManager();

    private Session session;

    public static SessionManager getInstance() {
        return sessionManager;
    }


    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

}
