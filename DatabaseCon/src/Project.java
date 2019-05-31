/**
 * -- ISTE 330 Group Project
 * -- Authors: Lowell Pence, Campbell Schweickhardt, Vincent Cheng
 */

import java.util.ArrayList;

/**
 * the data object class for a "Project" that will be stored in the database, created and maintain by a faculty member.
 */
public class Project {

    /**
     * the unique ID for a project
     */
    private int projID;

    /**
     * the name of a project, determined by the faculty member owner
     */
    private String projectName;

    /**
     * the list of contributors that are also working on a project (listed by their ID)
     */
    private ArrayList<String> contributors;

    /**
     * the list of associated keywords with a single project
     */
    private ArrayList<String> keywords;

    /**
     * the abstract text of a project
     */
    private String abstractText;

    /**
     * the owner's user ID of a project
     */
    private String ownerID;

    /**
     * Constructs a project data object
     * @param projID the auto generated projID as created by the database
     * @param projectName the name of the project
     * @param abstractText the abstract text to add to a project
     * @param ownerUsername the username ID of the owner of the new project
     */
    public Project(int projID, String projectName, String abstractText, String ownerUsername) {
        this.projectName = projectName;
        this.abstractText = abstractText;
        this.projID = projID;
        this.ownerID = ownerUsername;
    }

    /**
     * @return the project ID
     */
    public int getProjID() {
        return this.projID;
    }

    /**
     * @return the project name
     */
    public String getProjectName() {
        return this.projectName;
    }

    /**
     * @return the list of contributors
     */
    public ArrayList<String> getContributors() {
        return this.contributors;
    }

    /**
     * @return the abstract text of a project
     */
    public String getAbstractText() {
        return this.abstractText;
    }

    /**
     * @return the owner's ID
     */
    public String getOwnerID() {
        return this.ownerID;
    }

    /**
     * @return the list of associated keywords
     */
    public ArrayList<String> getKeywords() { return this.keywords; }

    /**
     * @return sets the project ID from the database when creating a new project
     */
    public void setProjID(int projID) {
        this.projID = projID;
    }

    /**
     * @return sets the project name for a new project
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * @return sets the abstract text for a new project
     */
    public void setAbstractText(String abstractText) {
        this.abstractText = abstractText;
    }

    /**
     * @return sets the owner ID for a new project
     */
    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
    }

    /**
     * @return adds a contributor to the list of contributors (by their ID)
     */
    public void addContributor(String contributor) {
        this.contributors.add(contributor);
    }

    /**
     * @return removes a contributor from the list
     */
    public void removeContributor(String contributor) {
        for (int i = 0; i < contributors.size(); i++) {
            if (contributors.get(i).equals(contributor)) {
                contributors.remove(i);
            }
        }
    }

    /**
     * @return adds a keyword to the list of ossociated keywords
     */
    public void addKeyword(String keyword) {
        this.keywords.add(keyword);
    }


}
