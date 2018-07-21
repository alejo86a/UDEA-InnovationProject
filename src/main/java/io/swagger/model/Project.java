package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

/**
 * Project
 */
@Validated

public class Project extends ResourceSupport {
  @JsonProperty("projectId")
  private Integer projectId = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("idPortFolio")
  private Integer idPortFolio = null;

  public Project id(Integer id) {
    this.projectId = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(example = "1", required = true, value = "")
  @NotNull


  public Integer getProjectId() {
    return projectId;
  }

  public void setProjectId(Integer id) {
    this.projectId = id;
  }

  public Project name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(example = "ACME Corporation", required = true, value = "")
  @NotNull


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Project description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
  **/
  @ApiModelProperty(example = "Project about technology", required = true, value = "")
  @NotNull


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Project idPortFolio(Integer idPortFolio) {
    this.idPortFolio = idPortFolio;
    return this;
  }

  /**
   * Get idPortFolio
   * @return idPortFolio
  **/
  @ApiModelProperty(example = "1", value = "")


  public Integer getIdPortFolio() {
    return idPortFolio;
  }

  public void setIdPortFolio(Integer idPortFolio) {
    this.idPortFolio = idPortFolio;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Project project = (Project) o;
    return Objects.equals(this.projectId, project.projectId) &&
        Objects.equals(this.name, project.name) &&
        Objects.equals(this.description, project.description) &&
        Objects.equals(this.idPortFolio, project.idPortFolio);
  }

  @Override
  public int hashCode() {
    return Objects.hash(projectId, name, description, idPortFolio);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Project {\n");
    
    sb.append("    id: ").append(toIndentedString(projectId)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    idPortFolio: ").append(toIndentedString(idPortFolio)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

