package com.example.trivia;

import java.util.ArrayList;
import java.util.Collections;

public class Collection2 {

    private ArrayList<Question> arr2;
    private int index; //מספר השאלה הבאה בתור

    public Collection2()
    {
        Question q1 = new Question("איך אומרים שלום באנגלית?","salam alecom","salom","helo","hello",4);
        Question q2 = new Question("איך אומרים ברוכים הבאים?","Welcom","yes","come","good",3);
        Question q3 = new Question("איך אומרים תודה באנגלית?","Thank you","well","tanks","very much",3);
        Question q4 = new Question("איך אומרים יפה באנגלית?","pretty","good","wowwwwww","pshiiiii",1);
        Question q5 = new Question("איך אומרים בית ספר באנגלית?","Preschool","School","Nononono","Yeseseseses",1);

        arr2 = new ArrayList<>();
        arr2.add(q1);
        arr2.add(q2);
        arr2.add(q3);
        arr2.add(q4);
        arr2.add(q5);

        Collections.shuffle(arr2);
    }
    public void initQuestion()
    {
        index = 0;
    }

    public Question getNextQuestion()
    {
        //הפעולה מחזירה הפניה לשאלה הבאה
        Question q = arr2.get(index);
        index++;
        return q;
    }

    public boolean isNotLastQuestion()
    {
        //הפעולה מחזירה אמת אם אנו בשאלה האחרונה
        return (index < arr2.size()); //if not at the end of the ArrayList
        //}
    }

    public int getIndex(){return index; }
}
