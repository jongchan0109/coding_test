package programmers.javasolve.level2.parkingfee;

import java.util.*;

class Solution {

    static Fee fee;

    public static class Fee {
        int defaultTime;
        int defaultFee;
        int unitTime;
        int unitFee;

        public Fee(int[] fees) {
            this.defaultTime = fees[0];
            this.defaultFee = fees[1];
            this.unitTime = fees[2];
            this.unitFee = fees[3];
        }
    }

    public static class Car {
        int number;
        int totalPark;
        int time;
        boolean state; // In인 경우 true, OUT인 경우 false;

        public Car(int number, int time) {
            this.number = number;
            this.time = time;
            this.state = true;
            this.totalPark = 0;
        }

        public void set(int time) {
            this.time = time;
            this.state = true;
        }

        public void out(int time) {
            totalPark += (time - this.time);
            this.state = false;
        }
    }

    public int calculate(int time) {
        time -= fee.defaultTime;
        if (time <= 0) {
            return fee.defaultFee;
        }
        int unitTime = (int) Math.ceil((double) time / fee.unitTime);
        return unitTime * fee.unitFee + fee.defaultFee;
    }


    public int[] solution(int[] fees, String[] records) {
        fee = new Fee(fees);
        HashMap<Integer, Car> carMap = new HashMap<>();
        List<Integer> carList = new ArrayList<>();

        for (String record : records) {
            String type = record.substring(11);
            int number = Integer.parseInt(record.substring(6, 10));
            int time = Integer.parseInt(record.substring(0, 2)) * 60 + Integer.parseInt(record.substring(3, 5));

            if (type.equals("IN")) {
                if (carMap.get(number) == null) {
                    Car car = new Car(number, time);
                    carList.add(number);
                    carMap.put(number, car);
                } else {
                    Car car = carMap.get(number);
                    car.set(time);
                }

            } else {
                Car car = carMap.get(number);
                car.out(time);
            }
        }

        for (int carNumber : carList) {
            if (carMap.get(carNumber).state) {
                carMap.get(carNumber).out(23 * 60 + 59);
            }
        }

        Collections.sort(carList);
        int[] answer = new int[carList.size()];

        for (int i = 0; i < carList.size(); i++) {
            answer[i] = calculate(carMap.get(carList.get(i)).totalPark);
        }

        return answer;
    }
}

public class Main {

    public static void main(String[] args) {
        int[] fees = {180, 5000, 10, 600};
        String[] records = {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT",
                "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};
        int[] result = new Solution().solution(fees,records);

        for (int i : result) {
            System.out.print(i + " ");
        }
    }
}
