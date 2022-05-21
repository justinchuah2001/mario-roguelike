package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.actors.*;
import game.ground.*;
import game.items.*;

/**
 * The main class for the Mario World game.
 */
public class Application {

  public static void main(String[] args) {

    World world = new World(new Display());
    HashMap<String, GameMap> worldList = new HashMap<>();

    FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(),
            new Sprout(), new WarpPipe(), new Lava(), new HealthFountain(), new PowerFountain());

    List<String> map = Arrays.asList(
            "C.........................................##..........+.........................",
            "............+............+..................#...................................",
            "............................................#...................................",
            ".............................................##......................+..........",
            "...............................C...............#................................",
            "................................................#...........C...................",
            ".................+................................#.............................",
            "............................................C....##.............................",
            "................................................##..............................",
            ".........+..............................+#____####.................+............",
            "...........C...........................+#_____###++.............................",
            ".......................................+#______###..............................",
            "........................................+#_____###..............................",
            "........................+........................##.......C.....+...............",
            "...C...............................................#............................",
            "....................................................#.....................C.....",
            "...................+...........C.....................#..........................",
            "...........................................H.A........#.........................",
            ".......................................................##.......................");

    GameMap gameMap = new GameMap(groundFactory, map);
    world.addGameMap(gameMap);
    worldList.put("World 1", gameMap);

    List<String> lavaMap = Arrays.asList(
            "C................................LLLLL.....###........+..........",
            "............+............+.......LLLLL......###..................",
            ".................................LLLLL.......###..............+..",
            ".................................LLLLL........###................",
            ".................................LLLLL.........###...............",
            ".................................LLLLL..........###..............",
            ".................+...............LLLLL..........###......+.......",
            ".................................L#L#L..........###..............",
            ".................................#L#L#.........###..+............",
            ".........+.......................L#L#L........###................",
            ".................................LLLLL........###................",
            ".................................LLLLL........###....+...........",
            "............................+....LLLLL.........###...............",
            "........................+........LLLLL..........###..............",
            ".................................LLLLL...........###......+......",
            ".................................LLLLL............###............",
            "...................+.............LLLLL.............###...........");


    GameMap lavaGameMap = new GameMap(groundFactory, lavaMap);
    world.addGameMap(lavaGameMap);
    worldList.put("World 8", lavaGameMap);

    Actor mario = new Player("Mario", 'm', 100, worldList);
    world.addPlayer(mario, gameMap.at(42, 10));
    gameMap.addActor(new FlyingKoopa(), gameMap.at(45,17));

    gameMap.addActor(new Toad(), gameMap.at(46, 10));
    lavaGameMap.addActor( new Bowser(), lavaGameMap.at(63, 16));
    lavaGameMap.addActor(new PrincessPeach(), lavaGameMap.at(64, 16));

    gameMap.at(42, 11).addItem(new SuperMushroom());
    gameMap.at(42, 9).addItem(new PowerStar());
    gameMap.at(43, 9).addItem(new Wrench());
    gameMap.at(43, 8).addItem(new Fire());
    gameMap.at(42,10).addItem(new FireFlower());

    world.run();

  }
}
