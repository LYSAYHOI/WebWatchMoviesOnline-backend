package com.kltn.RepositoryImpl;

import com.kltn.Model.Role;
import com.kltn.Model.RoleName;
import com.kltn.Repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class RoleRepository implements IRoleRepository {


    @Autowired
    private EntityManager entityManager;

    @Override
    public Role addRole(Role role) {
        try {
            entityManager.persist(role);
            entityManager.flush();
            return role;
        }catch(Exception e){
            System.out.println("error addRole");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean checkHasRole(Role role){
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Long> query = cb.createQuery(Long.class);
            Root root = query.from(Role.class);
            query.select(cb.count(root));
            query.where(cb.equal(root.get("roleName"), role.getRoleName()));
            return entityManager.createQuery(query).getSingleResult() > 0 ? true : false;
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Role getRole(RoleName roleName) {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Role> query = cb.createQuery(Role.class);
            Root root = query.from(Role.class);
            query.where(cb.equal(root.get("roleName"), roleName));
            return entityManager.createQuery(query).getSingleResult();
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
