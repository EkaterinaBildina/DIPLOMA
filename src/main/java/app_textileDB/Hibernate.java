package app_textileDB;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import textile.Textile;

import java.util.List;


public class Hibernate {
    static StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Textile.class)
                .buildSessionFactory(builder.build())){


            // Создание сессии
            Session session = sessionFactory.getCurrentSession();
            // Начало транзакции
            session.beginTransaction();

            // Создание объекта
            Textile textile = Textile.create();
            session.save(textile);
            System.out.println("Object textile SAVED successfully");

            // Чтение объекта из базы данных по ID
            Textile retrievedTextile = session.get(Textile.class, textile.getId());
            System.out.println("Object textile retrieved successfully");
            System.out.println("Retrieved textile object: " + retrievedTextile);

            // Обновление объекта
            retrievedTextile.updateName("Morning in the garden");
            retrievedTextile.updateQTY(1.5);
            session.update(retrievedTextile);
            System.out.println("Object textile update successfully");

            // Удаление объекта
            session.delete(retrievedTextile);
            System.out.println("Object textile delete successfully");

            // Результат
            List<Textile> textileList = session.createQuery("From textile").list();
            System.out.println(textileList);


            session.getTransaction().commit();
            session.close();


        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
