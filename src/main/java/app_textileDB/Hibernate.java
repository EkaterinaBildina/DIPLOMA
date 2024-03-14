package app_textileDB;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import textile.Textile;


public class Hibernate {
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Textile.class)
                .buildSessionFactory()){

            // Создание сессии
            Session session = sessionFactory.getCurrentSession();
            // Начало транзакции
            session.beginTransaction();

            // Создание объекта
            Textile textile = Textile.create();
            session.save(textile);
            System.out.println("Object textile save successfully");

            // Чтение объекта из базы данных
            Textile retrievedTextile = session.get(Textile.class, textile.getId());
            System.out.println("Object textile retrieved successfully");
            System.out.println("Retrieved textile object: " + retrievedTextile);

            // Обновление объекта
            retrievedTextile.updateName("Morning in the garden");
            retrievedTextile.updateQTY(1.5);
            session.update(retrievedTextile);
            System.out.println("Object textile update successfully");

            session.delete(retrievedTextile);
            System.out.println("Object textile delete successfully");


            session.getTransaction().commit();


        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
