package guru.springframework.controllers;

import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeServiceImpl;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;

public class IndexControllerTest extends TestCase {

    IndexController indexController;
    @Mock
    RecipeServiceImpl recipeService;

    @Mock
    Model model;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        indexController=new IndexController(recipeService);
    }

    @Test
    public void testGetIndexPage() {
//        Recipe recipe=new Recipe();
//        HashSet recipesData=new HashSet();
//        recipesData.add(recipe);


        Set<Recipe> recipes=new HashSet<>();
        recipes.add(new Recipe());
        Recipe recipe=new Recipe();
        recipe.setId(12L);
        recipes.add(recipe);

       when(recipeService.getRecipes()).thenReturn(recipes);
     String viewName=  indexController.getIndexPage(model);

        ArgumentCaptor<Set<Recipe>> argumentCaptor=ArgumentCaptor.forClass(Set.class);
      assertEquals("index",viewName);
       verify(recipeService,times(1)).getRecipes();
      verify(model,times(1)).addAttribute(eq("recipes"),argumentCaptor.capture());

      Set<Recipe> setInController=argumentCaptor.getValue();
      assertEquals(setInController.size(),2);
    }
}