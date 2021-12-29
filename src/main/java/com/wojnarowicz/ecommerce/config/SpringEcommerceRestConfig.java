package com.wojnarowicz.ecommerce.config;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.Type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.ExposureConfigurer.AggregateResourceHttpMethodsFilter;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.wojnarowicz.ecommerce.model.Country;
import com.wojnarowicz.ecommerce.model.Order;
import com.wojnarowicz.ecommerce.model.Product;
import com.wojnarowicz.ecommerce.model.ProductCategory;
import com.wojnarowicz.ecommerce.model.State;

@Configuration
public class SpringEcommerceRestConfig implements RepositoryRestConfigurer {

  @Value(value = "${allowed.origins}")
  private String[] allowedOrigins;
  
  private final EntityManager entityManager;
  
  @Autowired
  public SpringEcommerceRestConfig(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
    
    HttpMethod[] unsupportedMethods = {HttpMethod.DELETE, HttpMethod.PATCH, HttpMethod.POST, HttpMethod.PUT};
    
    AggregateResourceHttpMethodsFilter filter = (metadata, httpMethods) -> httpMethods.disable(unsupportedMethods);
    
    config.getExposureConfiguration()
    .forDomainType(Product.class)
    .withItemExposure(filter)
    .withCollectionExposure(filter);    

    config.getExposureConfiguration()
    .forDomainType(ProductCategory.class)
    .withItemExposure(filter)
    .withCollectionExposure(filter);

    config.getExposureConfiguration()
    .forDomainType(Country.class)
    .withItemExposure(filter)
    .withCollectionExposure(filter);
    

    config.getExposureConfiguration()
    .forDomainType(State.class)
    .withItemExposure(filter)
    .withCollectionExposure(filter);
    
    config.getExposureConfiguration()
    .forDomainType(Order.class)
    .withItemExposure(filter)
    .withCollectionExposure(filter);
    
    exposeIds(config);
    
    cors.addMapping(config.getBasePath() + "/**").allowedOrigins(allowedOrigins);
  }

  private void exposeIds(RepositoryRestConfiguration config) {
    config.exposeIdsFor(entityManager.getMetamodel().getEntities().stream().map(Type::getJavaType).toArray(Class[]::new));
  }
  
}
