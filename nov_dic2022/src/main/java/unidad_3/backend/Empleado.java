package unidad_3.backend;

public class Empleado {
    private String id;
    private String firstName;
    private String lastName;
    private String photo;

    public Empleado(String id, String firstName, String lastName, String photo) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.photo = photo;
    }

    public Empleado() {
        this.id = "default";
        this.firstName = "firstName";
        this.lastName = "lastName";
        this.photo = "photo";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return this.id + ";" + this.firstName + ";" + this.lastName + ";" + this.photo;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (!(o instanceof Empleado)) {
            return false;
        }

        Empleado other = (Empleado) o;

        if (other.getId().equals(this.getId()) && other.getFirstName().equals(this.getFirstName())  && other.getLastName().equals(this.getLastName()) && other.getPhoto().equals(this.getPhoto()) ) {
            return true;
        }
        return false;
    }

}
