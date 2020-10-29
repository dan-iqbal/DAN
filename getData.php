<?php

    function DBtukarJSON($nim){
        $connect = mysqli_connect('localhost','root','','sandbox');

        $query = 'SELECT * FROM mahasiswa WHERE NIM="'.$nim.'"';
        $query2 = "SELECT a.KODE , NAMA_KUL, SKS FROM matkul AS a INNER JOIN take ON a.KODE=take.KODE WHERE NIM='".$nim."'";

        $result = mysqli_query($connect,$query);
        $result2 = mysqli_query($connect,$query2);

        while($row = mysqli_fetch_assoc($result)){
            while($row2 = mysqli_fetch_assoc($result2)){
                $array[]=$row2;
            }
            $arr['MATAKULIAH'] = $array;
            $test[] = array_merge($row,$arr);
            $final['MAHASISWA'] = $test;
        }
        header('Content-Type: application/json');
        echo json_encode($final,JSON_PRETTY_PRINT);
        $data = json_encode($final);
        file_put_contents('Mahasiswa.json',$data);
    }

    $nim = '185150707111003';
    DBtukarJSON($nim);
?>
