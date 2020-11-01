package com.example.admin.fragment8;

import java.util.ArrayList;

public class NotNet {
    public ArrayList<Replacement> replacements = new ArrayList<>();
    public NotNet() {
        createReplacements();
    }
    private void createReplacements(){
                replacements.add(new Replacement("345",
                "2020","7","31","2","хімія",
                "Петренко О.П.","","1-025",
                "програмування","Сидоренко В.В.","",
                "3-012"));
    }
}
