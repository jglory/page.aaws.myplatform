<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    /**
     * Run the migrations.
     */
    public function up(): void
    {
        Schema::create('customer_asset', function (Blueprint $table) {
            $table->id()->comment('고유키');
            $table->unsignedBigInteger('customer_id')->index()->comment('고객 고유키');
            $table->string('name', 30)->comment('건물명');
            $table->string('address', 150)->comment('주소');
            $table->string('region', 20)->index()->comment('지역');
            $table->dateTime('created_at')->index();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('customer_asset');
    }
};
