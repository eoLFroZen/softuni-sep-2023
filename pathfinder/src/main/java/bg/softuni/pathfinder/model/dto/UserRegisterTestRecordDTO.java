package bg.softuni.pathfinder.model.dto;

public record UserRegisterTestRecordDTO(
        String username
) {

    public String getUsername() {
        return username;
    }
}
