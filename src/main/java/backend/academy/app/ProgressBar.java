package backend.academy.app;

import lombok.Getter;

public class ProgressBar {

    private final int total;
    @Getter
    private int current = 0;

    public ProgressBar(int total) {
        this.total = total;
    }

    public void increment() {
        current++;
        if (current % (total / 100) == 0) {
            System.out.print("\rПрогресс: " + (current * 100 / total) + "%");
        }
    }
}
