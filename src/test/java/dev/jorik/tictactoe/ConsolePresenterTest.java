package dev.jorik.tictactoe;

import dev.jorik.tictactoe.console.ConsolePresenter;
import dev.jorik.tictactoe.console.ConsoleView;
import dev.jorik.tictactoe.field.models.FieldDto;
import dev.jorik.tictactoe.field.models.Player;
import dev.jorik.tictactoe.game.models.Result;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

@RunWith(JUnit4.class)
public class ConsolePresenterTest {
    ConsoleView mockView;

    @Before
    public void setup(){
        mockView = Mockito.mock(ConsoleView.class);
    }

    @After
    public void teardown(){}

    @Test
    public void showMessage(){
        ConsolePresenter presenter = new ConsolePresenter(mockView);

        presenter.show("message");

        Mockito.verify(mockView).show("message\n");
    }

    @Test
    public void showTurnCross(){
        ConsolePresenter presenter = new ConsolePresenter(mockView);

        presenter.show(Player.CROSS);

        Mockito.verify(mockView).show("(Крестики) введите координаты ячейки: ");
    }

    @Test
    public void showTurnCircle(){
        ConsolePresenter presenter = new ConsolePresenter(mockView);

        presenter.show(Player.CIRCLE);

        Mockito.verify(mockView).show("(Нолики) введите координаты ячейки: ");
    }

    @Test
    public void showFieldEmpty(){
        ConsolePresenter presenter = new ConsolePresenter(mockView);

        presenter.show(new FieldDto("   \n   \n   \n"));

        Mockito.verify(mockView).show("   \n   \n   \n");
    }

    @Test
    public void showFieldCross(){
        ConsolePresenter presenter = new ConsolePresenter(mockView);

        presenter.show(new FieldDto("XXX\nXXX\nXXX\n"));

        Mockito.verify(mockView).show("XXX\nXXX\nXXX\n");
    }

    @Test
    public void showFieldCircle(){
        ConsolePresenter presenter = new ConsolePresenter(mockView);

        presenter.show(new FieldDto("OOO\nOOO\nOOO\n"));

        Mockito.verify(mockView).show("OOO\nOOO\nOOO\n");
    }

    @Test
    public void showResultDraw(){
        ConsolePresenter presenter = new ConsolePresenter(mockView);

        presenter.show(Result.DRAW);

        Mockito.verify(mockView).show("Игра закончена. Победитель: Ничья");
    }

    @Test
    public void showResultCross(){
        ConsolePresenter presenter = new ConsolePresenter(mockView);

        presenter.show(Result.CROSS);

        Mockito.verify(mockView).show("Игра закончена. Победитель: Крестики");
    }

    @Test
    public void showResultCircle(){
        ConsolePresenter presenter = new ConsolePresenter(mockView);

        presenter.show(Result.CIRCLE);

        Mockito.verify(mockView).show("Игра закончена. Победитель: Нолики");
    }
}

