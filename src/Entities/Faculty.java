package Entities;

public class Faculty {
    public int _passScore;

    public String _name;

    public String[] _disciplines;

    public Faculty(String name, int passScore, String[] disciplines) throws Exception {
        if(name.isEmpty()){
            throw new Exception("Empty name");
        }

        if(passScore<=0){
            throw new Exception("Wrong pass score");
        }

        _passScore = passScore;
        _name = name;
        _disciplines = disciplines;
    }
}
