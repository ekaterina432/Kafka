/*
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.List;

public class ZookeeperExemple {
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException{
        //Устанавливается конект к ZK, sessionTimeout - время ожидания ответа от сервера, event - событие сообщающее, что конект установился
        ZooKeeper zkClient = new ZooKeeper("localhost:2181",10000, event -> System.out.println(event.getState()));

        //для того, чтобы не заниматься синхронизацией данных с сервером, будет надеяться что конкт установится за 1 сек.
        Thread.sleep(1000);

        //создаем ноду, getBytes() - возвращает байты, ZooDefs.Ids.OPEN_ACL_UNSAFE - открытый доступ, CreateMode.PERSISTENT - сохранение
        zkClient.create("/test", "summer".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        // setData() - устанавливает данные в ноду,getBytes- преобразует строку winter в массив байтов. version -1 - как долго  ждать перед возвратом ошибки, если znode не может быть обновлен(-1 значит бесконечно)
        zkClient.setData("/test", "winter".getBytes(), -1);

        //получения данных, Watcher - вызывается, когда состояние znode изменяется (false вернет null, если znode не существует), null -  не хочу устанавливать наблюдатель.
        zkClient.getData("/test", false, null);

        //true- клиент должен установить наблюдатель (watcher)- если узел "/" или один из его дочерних узлов изменится, клиент будет уведомлен и сможет получить обновленный список дочерних узлов.
        List<String> zkNodes = zkClient.getChildren("/", true);
        System.out.println(zkNodes);
    }
}
*/
