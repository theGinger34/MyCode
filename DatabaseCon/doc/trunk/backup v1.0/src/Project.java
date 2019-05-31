/**
 * -- ISTE 330 Group Project
 * -- Authors: Lowell Pence, Campbell Schweickhardt, Vincent Cheng
 */

import java.util.ArrayList;

public class Project {

    private int projID;
    private String projectName;
    private ArrayList<String> contributors;
    private ArrayList<String> keywords;
    private String abstractText;
    private String ownerID;

    public Project(int projID, String projectName, String abstractText, String ownerUsername) {
        this.projectName = projectName;
        this.abstractText = abstractText;
        this.projID = projID;
        this.ownerID = ownerUsername;
    }

    public int getProjID() {
        return this.projID;
    }
    
    public String getProjectName() {
        return this.projectName;
    }

    public ArrayList<String> getContributors() {
        return this.contributors;
    }

    public String getAbstractText() {
        return this.abstractText;
    }
    
    public String getOwnerID() {
        return this.ownerID;
    }

    public ArrayList<String> getKeywords() { return this.keywords; }
    
    public void setProjID(int projID) {
        this.projID = projID;
    }
    
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setAbstractText(String abstractText) {
        this.abstractText = abstractText;
    }
    
    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
    }

    public void addContributor(String contributor) {
        this.contributors.add(contributor);
    }

    public void removeContributor(String contributor) {
        for (int i = 0; i < contributors.size(); i++) {
            if (contributors.get(i).equals(contributor)) {
                contributors.remove(i);
            }
        }
    }

    public void addKeyword(String keyword) {
        this.keywords.add(keyword);
    }


}
