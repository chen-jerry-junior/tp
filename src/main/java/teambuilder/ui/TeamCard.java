package teambuilder.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import teambuilder.model.team.Team;

/**
 * A UI component that displays information of a {@code Team}.
 */
public class TeamCard extends UiPart<Region> {

    private static final String FXML = "TeamListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Team team;

    @FXML
    private HBox cardPane;
    @FXML
    private Label teamName;
    @FXML
    private Label id;
    @FXML
    private Label teamDesc;
    @FXML
    private FlowPane skillTags;
    @FXML
    private FlowPane members;

    /**
     * Creates a {@code TeamCode} with the given {@code Team} and index to display.
     */
    public TeamCard(Team team, int displayedIndex) {
        super(FXML);
        this.team = team;
        id.setText(displayedIndex + ". ");
        teamName.setText(team.getName().teamName);
        teamDesc.setText(team.getDesc().value);
        team.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> skillTags.getChildren().add(new Label(tag.tagName)));
        team.getMembers().stream()
                .sorted(Comparator.comparing(member -> member.fullName))
                .forEach(member -> members.getChildren().add(new Label(member.fullName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonCard)) {
            return false;
        }

        // state check
        TeamCard card = (TeamCard) other;
        return id.getText().equals(card.id.getText())
                && team.equals(card.team);
    }
}
