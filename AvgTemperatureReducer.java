package avgTemp;


import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class AvgTemperatureReducer
extends Reducer<Text, IntWritable, Text, IntWritable> {

@Override
public void reduce(Text key, Iterable<IntWritable> values,
   Context context)
   throws IOException, InterruptedException {

	
	int total = 0;
    int count = 0;

    for (IntWritable tempValue : values) {
        total += tempValue.get();
        count++;
    }

    int avgValue = total / count;
    context.write(key, new IntWritable(avgValue));
}
}
