package com.epm.recipe.persistence.jdbc;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.persistence.RecipeRepository;
import com.epm.recipe.persistence.jdbc.exception.JdbcPersistenceException;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbRecipeRepository implements RecipeRepository {

    private final DataSource dataSource;

    public DbRecipeRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Recipe> findAll() {
        final String query = "SELECT * FROM recipes";
        List<Recipe> recipes = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                long id = resultSet.getInt("id");
                Recipe recipe = new Recipe(title, id);
                recipes.add(recipe);
            }
        } catch (SQLException e) {
            throw new JdbcPersistenceException(e.getMessage());
        }
        return recipes;
    }

    @Override
    public Recipe findById(long id) {
        final String query = "SELECT * FROM recipes where id=?";
        Recipe recipe = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                recipe = new Recipe(title, id);
            }
        } catch (SQLException e) {
            throw new JdbcPersistenceException(e.getMessage());
        }
        return recipe;
    }
}
