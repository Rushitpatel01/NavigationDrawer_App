package Models;

public class RegisterUser
{
    Integer connection;
    Integer result;

    public RegisterUser(Integer connection, Integer result) {
        this.connection = connection;
        this.result = result;
    }

    public Integer getConnection() {
        return connection;
    }

    public void setConnection(Integer connection) {
        this.connection = connection;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "RegisterUser{" +
                "connection=" + connection +
                ", result=" + result +
                '}';
    }
}
